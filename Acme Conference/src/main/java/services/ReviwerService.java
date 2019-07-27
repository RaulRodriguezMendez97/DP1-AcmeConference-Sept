
package services;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReviwerRepository;
import security.Authority;
import security.UserAccount;
import domain.Reviwer;

@Service
@Transactional
public class ReviwerService {

	@Autowired
	private ReviwerRepository	reviwerRepository;
	@Autowired
	private ActorService		actorService;


	public Reviwer getReviwerByUserAccount(final Integer userAccountId) {
		return this.reviwerRepository.getReviwerByUserAccount(userAccountId);
	}

	public Reviwer create() {
		final Reviwer reviwer = new Reviwer();
		reviwer.setName("");
		reviwer.setMiddleName("");
		reviwer.setSurname("");
		reviwer.setPhoto("");
		reviwer.setEmail("");
		reviwer.setPhone("");
		reviwer.setAddress("");
		reviwer.setKeyWords(new HashSet<String>());

		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.REVIWER);
		user.getAuthorities().add(ad);

		//NUEVO
		user.setUsername("");
		user.setPassword("");

		reviwer.setUserAccount(user);
		return reviwer;
	}

	public Reviwer save(final Reviwer r) {

		//final UserAccount userLoged = LoginService.getPrincipal();
		//Assert.isTrue(userLoged.getAuthorities().iterator().next().getAuthority().equals("COMPANY"), "Comprobar que hay Company conectado");
		Reviwer res = null;

		Assert.isTrue(r != null && r.getName() != null && r.getSurname() != null && r.getName() != "" && r.getUserAccount() != null && r.getEmail() != null && r.getEmail() != "", "Company.save -> Name, Surname or email invalid");

		final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern patternEmail1 = Pattern.compile(regexEmail1);
		final Matcher matcherEmail1 = patternEmail1.matcher(r.getEmail());

		final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
		final Pattern patternEmail2 = Pattern.compile(regexEmail2);
		final Matcher matcherEmail2 = patternEmail2.matcher(r.getEmail());
		Assert.isTrue(matcherEmail1.find() == true || matcherEmail2.find() == true, "CustomerService.save -> Correo inválido");

		final List<String> emails = this.actorService.getEmails();

		if (r.getId() == 0)
			Assert.isTrue(!emails.contains(r.getEmail()), "Customer.Email -> The email you entered is already being used");
		//		else {
		//			final Company a = this.companyRepository.findOne(r.getId());
		//			Assert.isTrue(a.getEmail().equals(r.getEmail()));
		//		}

		//NUEVO
		Assert.isTrue(r.getUserAccount().getUsername() != null && r.getUserAccount().getUsername() != "");
		Assert.isTrue(r.getUserAccount().getPassword() != null && r.getUserAccount().getPassword() != "");

		if (r.getId() == 0) {

			final Md5PasswordEncoder encoder;
			encoder = new Md5PasswordEncoder();
			final String hash = encoder.encodePassword(r.getUserAccount().getPassword(), null);
			final UserAccount user = r.getUserAccount();
			user.setPassword(hash);
		}

		res = this.reviwerRepository.save(r);
		return res;
	}

}

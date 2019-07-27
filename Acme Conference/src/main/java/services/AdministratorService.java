
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

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	administratorRepository;
	@Autowired
	private ActorService			actorService;


	public Administrator getAdministratorByUserAccount(final Integer userAccountId) {
		return this.administratorRepository.getAdministratorByUserAccount(userAccountId);
	}

	public Administrator create() {
		final Administrator admin = new Administrator();
		admin.setName("");
		admin.setMiddleName("");
		admin.setSurname("");
		admin.setPhoto("");
		admin.setEmail("");
		admin.setPhone("");
		admin.setAddress("");

		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.ADMIN);
		user.getAuthorities().add(ad);

		//NUEVO
		user.setUsername("");
		user.setPassword("");

		admin.setUserAccount(user);
		return admin;
	}

	public Administrator save(final Administrator admin) {
		final UserAccount userLoged = LoginService.getPrincipal();
		Assert.isTrue(userLoged.getAuthorities().iterator().next().getAuthority().equals("ADMIN"), "Comprobar que hay admin conectado");

		Administrator res = null;
		Assert.isTrue(admin.getName() != null && admin.getName() != "" && admin.getSurname() != null && admin.getUserAccount() != null && admin.getEmail() != null && admin.getEmail() != "", "Fallo en datos personales");

		final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern patternEmail1 = Pattern.compile(regexEmail1);
		final Matcher matcherEmail1 = patternEmail1.matcher(admin.getEmail());

		final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
		final Pattern patternEmail2 = Pattern.compile(regexEmail2);
		final Matcher matcherEmail2 = patternEmail2.matcher(admin.getEmail());

		final String regexEmail3 = "^[A-z0-9]+\\@$";
		final Pattern patternEmail3 = Pattern.compile(regexEmail3);
		final Matcher matcherEmail3 = patternEmail3.matcher(admin.getEmail());

		final String regexEmail4 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@\\>$";
		final Pattern patternEmail4 = Pattern.compile(regexEmail4);
		final Matcher matcherEmail4 = patternEmail4.matcher(admin.getEmail());

		Assert.isTrue((matcherEmail1.matches() == true || matcherEmail2.matches() == true || matcherEmail3.matches() == true || matcherEmail4.matches() == true), "Email");

		final List<String> emails = this.actorService.getEmails();

		if (admin.getId() == 0)
			Assert.isTrue(!emails.contains(admin.getEmail()));
		//		else {
		//			final Administrator a = this.adminRepo.findOne(admin.getId());
		//			Assert.isTrue(a.getEmail().equals(admin.getEmail()));
		//		}

		//NUEVO
		Assert.isTrue(admin.getUserAccount().getUsername() != null && admin.getUserAccount().getUsername() != "", "Cuenta");
		Assert.isTrue(admin.getUserAccount().getPassword() != null && admin.getUserAccount().getPassword() != "", "Cuenta");

		if (admin.getId() == 0) {
			final Md5PasswordEncoder encoder;
			encoder = new Md5PasswordEncoder();
			final String hash = encoder.encodePassword(admin.getUserAccount().getPassword(), null);
			final UserAccount user = admin.getUserAccount();
			user.setPassword(hash);
		}

		res = this.administratorRepository.save(admin);

		return res;
	}

}

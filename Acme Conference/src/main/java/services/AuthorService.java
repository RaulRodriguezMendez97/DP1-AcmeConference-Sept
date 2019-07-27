
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

import repositories.AuthorRepository;
import security.Authority;
import security.UserAccount;
import domain.Author;
import domain.Paper;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepository	authorRepository;
	@Autowired
	private ActorService		actorService;


	public Author getAuthorByUserAccount(final Integer userAccountId) {
		return this.authorRepository.getAuthorByUserAccount(userAccountId);
	}

	public Author create() {
		final Author author = new Author();
		author.setName("");
		author.setMiddleName("");
		author.setSurname("");
		author.setPhoto("");
		author.setEmail("");
		author.setPhone("");
		author.setAddress("");
		author.setPapers(new HashSet<Paper>());

		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.AUTHOR);
		user.getAuthorities().add(ad);

		//NUEVO
		user.setUsername("");
		user.setPassword("");

		author.setUserAccount(user);
		return author;
	}

	public Author save(final Author a) {
		Author res = null;
		Assert.isTrue(a != null && a.getName() != null && a.getSurname() != null && a.getName() != "" && a.getUserAccount() != null && a.getEmail() != null && a.getEmail() != "", "Company.save -> Name, Surname or email invalid");
		//Assert.isTrue(a.getCreditCard() != null, "Customer.save -> CreditCard  invalid");

		final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern patternEmail1 = Pattern.compile(regexEmail1);
		final Matcher matcherEmail1 = patternEmail1.matcher(a.getEmail());

		final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
		final Pattern patternEmail2 = Pattern.compile(regexEmail2);
		final Matcher matcherEmail2 = patternEmail2.matcher(a.getEmail());
		Assert.isTrue(matcherEmail1.find() == true || matcherEmail2.find() == true, "CustomerService.save -> Correo inválido");

		final List<String> emails = this.actorService.getEmails();

		if (a.getId() == 0)
			Assert.isTrue(!emails.contains(a.getEmail()), "Customer.Email -> The email you entered is already being used");

		//NUEVO
		Assert.isTrue(a.getUserAccount().getUsername() != null && a.getUserAccount().getUsername() != "");
		Assert.isTrue(a.getUserAccount().getPassword() != null && a.getUserAccount().getPassword() != "");

		if (a.getId() == 0) {
			final Md5PasswordEncoder encoder;
			encoder = new Md5PasswordEncoder();
			final String hash = encoder.encodePassword(a.getUserAccount().getPassword(), null);
			final UserAccount user = a.getUserAccount();
			user.setPassword(hash);
		}
		res = this.authorRepository.save(a);
		return res;
	}
}

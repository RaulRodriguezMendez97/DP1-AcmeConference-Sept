
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AuthorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Author;
import domain.Paper;
import forms.RegistrationFormAuthor;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepository	authorRepository;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private Validator			validator;


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

	public Author findOne(final int authorId) {
		final Author author = this.authorRepository.findOne(authorId);
		final UserAccount userLoged = LoginService.getPrincipal();
		final Actor a = this.actorService.getActorByUserAccount(userLoged.getId());
		Assert.isTrue(userLoged.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
		Assert.isTrue(author.equals(a));
		return this.authorRepository.findOne(authorId);
	}

	public Author save(final Author a) {
		Author res = null;
		Assert.isTrue(a != null && a.getName() != null && a.getSurname() != null && a.getName() != "" && a.getUserAccount() != null && a.getEmail() != null && a.getEmail() != "", "Company.save -> Name, Surname or email invalid");

		final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern patternEmail1 = Pattern.compile(regexEmail1);
		final Matcher matcherEmail1 = patternEmail1.matcher(a.getEmail());

		final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
		final Pattern patternEmail2 = Pattern.compile(regexEmail2);
		final Matcher matcherEmail2 = patternEmail2.matcher(a.getEmail());
		Assert.isTrue(matcherEmail1.find() == true || matcherEmail2.find() == true, "AuthorService.save -> Correo inválido");

		final List<String> emails = this.actorService.getEmails();

		if (a.getId() == 0)
			Assert.isTrue(!emails.contains(a.getEmail()), "Author.Email -> The email you entered is already being used");

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
	public final Author reconstruct(final RegistrationFormAuthor registrationForm, final BindingResult binding) {
		Author res = new Author();

		if (registrationForm.getId() == 0) {
			res.setId(registrationForm.getUserAccount().getId());
			res.setVersion(registrationForm.getVersion());
			res.setAddress(registrationForm.getAddress());
			res.setEmail(registrationForm.getEmail());
			res.setName(registrationForm.getName());
			res.setPhone(registrationForm.getPhone());
			res.setPhoto(registrationForm.getPhoto());
			res.setSurname(registrationForm.getSurname());
			res.setPapers(new HashSet<Paper>());
			res.setMiddleName(registrationForm.getMiddleName());

			final Authority ad = new Authority();
			final UserAccount user = new UserAccount();
			user.setAuthorities(new HashSet<Authority>());
			ad.setAuthority(Authority.AUTHOR);
			user.getAuthorities().add(ad);
			res.setUserAccount(user);
			user.setUsername(registrationForm.getUserAccount().getUsername());
			user.setPassword(registrationForm.getUserAccount().getPassword());

			Assert.isTrue(registrationForm.getPassword().equals(registrationForm.getUserAccount().getPassword()));

			if (res.getPhone().length() <= 5)
				res.setPhone("");

			if (registrationForm.getPatternPhone() == false) {
				final String regexTelefono = "^\\+[0-9]{0,3}\\s\\([0-9]{0,3}\\)\\ [0-9]{4,}$|^\\+[1-9][0-9]{0,2}\\ [0-9]{4,}$|^[0-9]{4,}|^\\+[0-9]\\ $|^$|^\\+$";
				final Pattern patternTelefono = Pattern.compile(regexTelefono);
				final Matcher matcherTelefono = patternTelefono.matcher(res.getPhone());
				Assert.isTrue(matcherTelefono.find() == true, "CompanyService.save -> Telefono no valido");
			}

			final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
			final Pattern patternEmail1 = Pattern.compile(regexEmail1);
			final Matcher matcherEmail1 = patternEmail1.matcher(res.getEmail());

			final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
			final Pattern patternEmail2 = Pattern.compile(regexEmail2);
			final Matcher matcherEmail2 = patternEmail2.matcher(res.getEmail());

			if (!(matcherEmail1.find() == true || matcherEmail2.find() == true))
				binding.rejectValue("email", "PatternNoValido");

			this.validator.validate(res, binding);

		} else {
			res = this.authorRepository.findOne(registrationForm.getId());
			final Author p = new Author();

			if (registrationForm.getUserAccount().getPassword().equals("") && registrationForm.getPassword().equals(""))
				p.setUserAccount(res.getUserAccount());
			else {
				final UserAccount user = registrationForm.getUserAccount();
				final Md5PasswordEncoder encoder;
				encoder = new Md5PasswordEncoder();
				final String hash = encoder.encodePassword(registrationForm.getUserAccount().getPassword(), null);
				user.setPassword(hash);
				registrationForm.setUserAccount(user);

				if (!registrationForm.getUserAccount().getPassword().equals(res.getUserAccount().getPassword())) {
					final Md5PasswordEncoder encoder2;
					encoder2 = new Md5PasswordEncoder();
					final String hash2 = encoder2.encodePassword(registrationForm.getPassword(), null);
					registrationForm.setPassword(hash2);
					Assert.isTrue(registrationForm.getPassword().equals(registrationForm.getUserAccount().getPassword()));

				}

				p.setUserAccount(res.getUserAccount());
				p.getUserAccount().setPassword(registrationForm.getUserAccount().getPassword());

			}

			p.setId(res.getId());
			p.setVersion(res.getVersion());
			p.setAddress(registrationForm.getAddress());
			p.setEmail(registrationForm.getEmail());

			p.setName(registrationForm.getName());
			p.setPhone(registrationForm.getPhone());
			p.setPhoto(registrationForm.getPhoto());
			p.setSurname(registrationForm.getSurname());
			p.setPapers(res.getPapers());
			p.setMiddleName(registrationForm.getMiddleName());

			if (p.getPhone().length() <= 5)
				p.setPhone("");

			final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
			final Pattern patternEmail1 = Pattern.compile(regexEmail1);
			final Matcher matcherEmail1 = patternEmail1.matcher(p.getEmail());

			final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
			final Pattern patternEmail2 = Pattern.compile(regexEmail2);
			final Matcher matcherEmail2 = patternEmail2.matcher(p.getEmail());

			if (!(matcherEmail1.find() == true || matcherEmail2.find() == true))
				binding.rejectValue("email", "PatternNoValido");

			if (registrationForm.getPatternPhone() == false) {
				final String regexTelefono = "^\\+[0-9]{0,3}\\s\\([0-9]{0,3}\\)\\ [0-9]{4,}$|^\\+[1-9][0-9]{0,2}\\ [0-9]{4,}$|^[0-9]{4,}|^\\+[0-9]\\ $|^$|^\\+$";
				final Pattern patternTelefono = Pattern.compile(regexTelefono);
				final Matcher matcherTelefono = patternTelefono.matcher(p.getPhone());
				Assert.isTrue(matcherTelefono.find() == true, "CompanyService.save -> Telefono no valido");
			}

			p.getUserAccount().setUsername(registrationForm.getUserAccount().getUsername());

			this.validator.validate(p, binding);
			res = p;

		}
		return res;

	}
}

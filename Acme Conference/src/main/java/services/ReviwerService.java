
package services;

import java.util.Collection;
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

import repositories.ReviwerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Reviwer;
import forms.RegistrationFormReviwer;

@Service
@Transactional
public class ReviwerService {

	@Autowired
	private ReviwerRepository	reviwerRepository;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private Validator			validator;


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
	public Reviwer findOne(final int reviwerId) {
		final Reviwer reviwer = this.reviwerRepository.findOne(reviwerId);
		final UserAccount userLoged = LoginService.getPrincipal();
		final Actor a = this.actorService.getActorByUserAccount(userLoged.getId());
		Assert.isTrue(userLoged.getAuthorities().iterator().next().getAuthority().equals("REVIWER"));
		Assert.isTrue(reviwer.equals(a));
		return this.reviwerRepository.findOne(reviwerId);
	}

	public Collection<Reviwer> findAll() {
		return this.reviwerRepository.findAll();
	}
	public Reviwer save(final Reviwer r) {

		Reviwer res = null;

		Assert.isTrue(r != null && r.getName() != null && r.getSurname() != null && r.getName() != "" && r.getUserAccount() != null && r.getEmail() != null && r.getEmail() != "", "Company.save -> Name, Surname or email invalid");

		final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern patternEmail1 = Pattern.compile(regexEmail1);
		final Matcher matcherEmail1 = patternEmail1.matcher(r.getEmail());

		final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
		final Pattern patternEmail2 = Pattern.compile(regexEmail2);
		final Matcher matcherEmail2 = patternEmail2.matcher(r.getEmail());
		Assert.isTrue(matcherEmail1.find() == true || matcherEmail2.find() == true, "ReviwerService.save -> Correo inválido");

		final List<String> emails = this.actorService.getEmails();

		if (r.getId() == 0)
			Assert.isTrue(!emails.contains(r.getEmail()), "Reviwer.Email -> The email you entered is already being used");
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

	public Reviwer reconstruct(final RegistrationFormReviwer registrationForm, final BindingResult binding) {
		Reviwer res = new Reviwer();

		if (registrationForm.getId() == 0) {
			res.setId(registrationForm.getUserAccount().getId());
			res.setVersion(registrationForm.getVersion());
			res.setAddress(registrationForm.getAddress());
			res.setEmail(registrationForm.getEmail());
			res.setName(registrationForm.getName());
			res.setPhone(registrationForm.getPhone());
			res.setPhoto(registrationForm.getPhoto());
			res.setSurname(registrationForm.getSurname());
			res.setKeyWords(new HashSet<String>());
			res.setMiddleName(registrationForm.getMiddleName());

			final Authority ad = new Authority();
			final UserAccount user = new UserAccount();
			user.setAuthorities(new HashSet<Authority>());
			ad.setAuthority(Authority.REVIWER);
			user.getAuthorities().add(ad);
			res.setUserAccount(user);
			user.setUsername(registrationForm.getUserAccount().getUsername());
			user.setPassword(registrationForm.getUserAccount().getPassword());

			Assert.isTrue(registrationForm.getPassword().equals(registrationForm.getUserAccount().getPassword()));

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
			res = this.reviwerRepository.findOne(registrationForm.getId());
			final Reviwer p = new Reviwer();

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
			p.setKeyWords(res.getKeyWords());
			p.setMiddleName(registrationForm.getMiddleName());

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

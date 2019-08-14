
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.AdministratorService;
import services.AuthorService;
import services.CreditCardService;
import services.ReviwerService;
import domain.Actor;
import domain.Administrator;
import domain.Author;
import domain.Reviwer;
import forms.RegistrationFormAdmin;
import forms.RegistrationFormAuthor;
import forms.RegistrationFormReviwer;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	@Autowired
	private ActorService			actorService;
	@Autowired
	private AdministratorService	adminService;
	@Autowired
	private CreditCardService		creditCardService;
	@Autowired
	private AuthorService			authorService;
	@Autowired
	private ReviwerService			reviwerService;


	//VER SUS DATOS PERSONALES
	@RequestMapping(value = "/personal-datas", method = RequestMethod.GET)
	public ModelAndView action2() {
		ModelAndView result;
		Actor a;

		final UserAccount user = LoginService.getPrincipal();
		a = this.actorService.getActorByUserAccount(user.getId());
		result = new ModelAndView("profile/action-1");
		result.addObject("actor", a);

		return result;
	}
	@RequestMapping(value = "/edit-administrator", method = RequestMethod.GET)
	public ModelAndView editAdmin() {
		ModelAndView result;
		final RegistrationFormAdmin registrationForm = new RegistrationFormAdmin();
		Administrator admin;

		try {

			admin = this.adminService.findOne(this.adminService.getAdministratorByUserAccount(LoginService.getPrincipal().getId()).getId());

			Assert.notNull(admin);
			registrationForm.setId(admin.getId());
			registrationForm.setVersion(admin.getVersion());
			registrationForm.setName(admin.getName());
			registrationForm.setMiddleName(admin.getMiddleName());
			registrationForm.setSurname(admin.getSurname());
			registrationForm.setPhoto(admin.getPhoto());
			registrationForm.setEmail(admin.getEmail());
			registrationForm.setPhone(admin.getPhone());

			registrationForm.setAddress(admin.getAddress());
			registrationForm.setPassword(admin.getUserAccount().getPassword());
			registrationForm.setPatternPhone(false);
			final UserAccount userAccount = new UserAccount();
			userAccount.setUsername(admin.getUserAccount().getUsername());
			userAccount.setPassword(admin.getUserAccount().getPassword());
			registrationForm.setUserAccount(userAccount);

			result = new ModelAndView("profile/editAdmin");
			result.addObject("actor", registrationForm);
			result.addObject("action", "profile/edit-administrator.do");

		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");
		}

		return result;

	}

	@RequestMapping(value = "/edit-administrator", method = RequestMethod.POST, params = "save")
	public ModelAndView editAdmin(@ModelAttribute("actor") final RegistrationFormAdmin registrationForm, final BindingResult binding) {
		ModelAndView result;

		try {

			final Administrator admin = this.adminService.reconstruct(registrationForm, binding);
			if (!binding.hasErrors()) {

				this.adminService.save(admin);

				result = new ModelAndView("redirect:personal-datas.do");
			} else {
				result = new ModelAndView("profile/editAdmin");
				result.addObject("actor", registrationForm);

			}
		} catch (final Exception e) {

			result = new ModelAndView("profile/editAdmin");
			result.addObject("actor", registrationForm);
			result.addObject("exception", e);

		}
		return result;

	}
	//
	@RequestMapping(value = "/edit-author", method = RequestMethod.GET)
	public ModelAndView editAuthor() {
		ModelAndView result;
		final RegistrationFormAuthor registrationForm = new RegistrationFormAuthor();
		Author author;

		try {

			author = this.authorService.findOne(this.authorService.getAuthorByUserAccount(LoginService.getPrincipal().getId()).getId());

			Assert.notNull(author);
			registrationForm.setId(author.getId());
			registrationForm.setVersion(author.getVersion());
			registrationForm.setName(author.getName());
			registrationForm.setMiddleName(author.getMiddleName());
			registrationForm.setSurname(author.getSurname());
			registrationForm.setPhoto(author.getPhoto());
			registrationForm.setEmail(author.getEmail());
			registrationForm.setPhone(author.getPhone());
			registrationForm.setAddress(author.getAddress());
			registrationForm.setPassword(author.getUserAccount().getPassword());

			registrationForm.setPatternPhone(false);
			final UserAccount userAccount = new UserAccount();
			userAccount.setUsername(author.getUserAccount().getUsername());
			userAccount.setPassword(author.getUserAccount().getPassword());
			registrationForm.setUserAccount(userAccount);
			result = new ModelAndView("profile/editAuthor");
			result.addObject("actor", registrationForm);
			result.addObject("action", "profile/edit-author.do");

		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");
		}

		return result;

	}

	@RequestMapping(value = "/edit-author", method = RequestMethod.POST, params = "save")
	public ModelAndView editAuthor(@ModelAttribute("actor") final RegistrationFormAuthor registrationForm, final BindingResult binding) {
		ModelAndView result;

		try {

			final Author author = this.authorService.reconstruct(registrationForm, binding);
			if (!binding.hasErrors()) {

				this.authorService.save(author);

				result = new ModelAndView("redirect:personal-datas.do");
			} else {
				result = new ModelAndView("profile/editAuthor");
				result.addObject("actor", registrationForm);

			}
		} catch (final Exception e) {

			result = new ModelAndView("profile/editAuthor");
			result.addObject("actor", registrationForm);
			result.addObject("exception", e);

		}
		return result;

	}

	@RequestMapping(value = "/edit-reviwer", method = RequestMethod.GET)
	public ModelAndView editReviwer() {
		ModelAndView result;
		final RegistrationFormReviwer registrationForm = new RegistrationFormReviwer();
		Reviwer reviwer;

		try {

			reviwer = this.reviwerService.findOne(this.reviwerService.getReviwerByUserAccount(LoginService.getPrincipal().getId()).getId());

			Assert.notNull(reviwer);
			registrationForm.setId(reviwer.getId());
			registrationForm.setVersion(reviwer.getVersion());
			registrationForm.setName(reviwer.getName());
			registrationForm.setMiddleName(reviwer.getMiddleName());
			registrationForm.setSurname(reviwer.getSurname());
			registrationForm.setPhoto(reviwer.getPhoto());
			registrationForm.setEmail(reviwer.getEmail());
			registrationForm.setPhone(reviwer.getPhone());
			registrationForm.setAddress(reviwer.getAddress());
			registrationForm.setPassword(reviwer.getUserAccount().getPassword());

			registrationForm.setPatternPhone(false);
			final UserAccount userAccount = new UserAccount();
			userAccount.setUsername(reviwer.getUserAccount().getUsername());
			userAccount.setPassword(reviwer.getUserAccount().getPassword());
			registrationForm.setUserAccount(userAccount);
			result = new ModelAndView("profile/editReviwer");
			result.addObject("actor", registrationForm);
			result.addObject("action", "profile/edit-reviwer.do");

		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");
		}

		return result;

	}

	@RequestMapping(value = "/edit-reviwer", method = RequestMethod.POST, params = "save")
	public ModelAndView editReviwer(@ModelAttribute("actor") final RegistrationFormReviwer registrationForm, final BindingResult binding) {
		ModelAndView result;

		try {

			final Reviwer reviwer = this.reviwerService.reconstruct(registrationForm, binding);
			if (!binding.hasErrors()) {

				this.reviwerService.save(reviwer);

				result = new ModelAndView("redirect:personal-datas.do");
			} else {
				result = new ModelAndView("profile/editReviwer");
				result.addObject("actor", registrationForm);

			}
		} catch (final Exception e) {

			result = new ModelAndView("profile/editReviwer");
			result.addObject("actor", registrationForm);
			result.addObject("exception", e);

		}
		return result;

	}

}

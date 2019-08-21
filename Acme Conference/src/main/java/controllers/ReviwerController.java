
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomizableSystemService;
import services.ReviwerService;
import domain.Reviwer;
import forms.RegistrationFormReviwer;

@Controller
@RequestMapping("/reviwer")
public class ReviwerController extends AbstractController {

	@Autowired
	private ReviwerService				reviwerService;

	@Autowired
	private CustomizableSystemService	customizableService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createForm() {
		ModelAndView result;
		RegistrationFormReviwer registrationForm = new RegistrationFormReviwer();

		registrationForm = registrationForm.createToReviwer();

		final String telephoneCode = this.customizableService.getTelephoneCode();
		registrationForm.setPhone(telephoneCode + " ");

		result = new ModelAndView("reviwer/create");
		result.addObject("registrationForm", registrationForm);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("registrationForm") final RegistrationFormReviwer registrationForm, final BindingResult binding) {
		ModelAndView result;
		Reviwer reviwer = null;

		try {

			reviwer = this.reviwerService.reconstruct(registrationForm, binding);

			if (!binding.hasErrors() && registrationForm.getUserAccount().getPassword().equals(registrationForm.getPassword())) {

				this.reviwerService.save(reviwer);
				result = new ModelAndView("redirect:/");
			} else {

				result = new ModelAndView("reviwer/create");
				result.addObject("registrationForm", registrationForm);
			}
		} catch (final Exception e) {

			result = new ModelAndView("reviwer/create");
			result.addObject("exception", e);
			result.addObject("registrationForm", registrationForm);

		}

		return result;
	}
}

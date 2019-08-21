
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import services.CreditCardService;
import services.CustomizableSystemService;
import services.RegistrationService;
import domain.Conference;
import domain.CreditCard;
import domain.Registration;
import forms.RegistrationAndCreditCardForm;

@Controller
@RequestMapping("/registration/author")
public class RegistrationAuthorController {

	@Autowired
	private RegistrationService			registrationService;

	@Autowired
	private ConferenceService			conferenceService;

	@Autowired
	private CustomizableSystemService	customizableService;
	@Autowired
	private CreditCardService			creditCardService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Registration> registrations;

		registrations = this.registrationService.getAllRegistrationByAuthor();

		result = new ModelAndView("registration/list");
		result.addObject("registrations", registrations);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createForm() {
		ModelAndView result;

		final Collection<Conference> conferences = this.conferenceService.getIncomingConferences();
		final Collection<Conference> conferencesAuthor = this.conferenceService.getAllConferenceByAuthor();
		conferences.removeAll(conferencesAuthor);
		final Collection<String> marcas = this.customizableService.getBrandNameCreditCard();
		final RegistrationAndCreditCardForm registrationForm = new RegistrationAndCreditCardForm();
		final Collection<CreditCard> myCreditCards = this.creditCardService.getCreditCardByAuthor();

		result = new ModelAndView("registration/create");
		result.addObject("conferences", conferences);
		result.addObject("marcas", marcas);
		result.addObject("registrationForm", registrationForm);
		result.addObject("myCreditCards", myCreditCards);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("registrationForm") final RegistrationAndCreditCardForm registrationForm, final BindingResult binding) {
		ModelAndView result;
		final CreditCard creditCard;
		final Registration res;

		try {

			creditCard = this.creditCardService.reconstruct(registrationForm, binding);
			registrationForm.setCreditCard(creditCard);
			res = this.registrationService.reconstruct(registrationForm, binding);
			if (!binding.hasErrors()) {
				final CreditCard creditCardSave = this.creditCardService.save(creditCard);
				res.setCreditCard(creditCardSave);
				this.registrationService.save(res);
				result = new ModelAndView("redirect:list.do");
			} else {
				final Collection<Conference> conferences = this.conferenceService.getIncomingConferences();
				final Collection<Conference> conferencesAuthor = this.conferenceService.getAllConferenceByAuthor();
				conferences.removeAll(conferencesAuthor);
				final Collection<String> marcas = this.customizableService.getBrandNameCreditCard();
				final Collection<CreditCard> myCreditCards = this.creditCardService.getCreditCardByAuthor();

				result = new ModelAndView("registration/create");
				result.addObject("registrationForm", registrationForm);
				result.addObject("conferences", conferences);
				result.addObject("marcas", marcas);
				result.addObject("myCreditCards", myCreditCards);
			}
		} catch (final Exception e) {
			final Collection<Conference> conferences = this.conferenceService.getIncomingConferences();
			final Collection<Conference> conferencesAuthor = this.conferenceService.getAllConferenceByAuthor();
			conferences.removeAll(conferencesAuthor);
			final Collection<String> marcas = this.customizableService.getBrandNameCreditCard();
			final Collection<CreditCard> myCreditCards = this.creditCardService.getCreditCardByAuthor();

			result = new ModelAndView("registration/create");
			result.addObject("exception", e);
			result.addObject("registrationForm", registrationForm);
			result.addObject("conferences", conferences);
			result.addObject("marcas", marcas);
			result.addObject("myCreditCards", myCreditCards);

		}

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer idRegistration) {
		ModelAndView result;

		final Registration registration = this.registrationService.findOne(idRegistration);
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("registration/show");
		result.addObject("registration", registration);
		result.addObject("lang", lang);

		return result;
	}

}

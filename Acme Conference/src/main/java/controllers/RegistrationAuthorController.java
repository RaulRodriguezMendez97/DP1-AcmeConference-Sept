
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.ConferenceService;
import services.CreditCardService;
import services.CustomizableSystemService;
import services.RegistrationService;
import domain.Conference;
import domain.CreditCard;
import domain.Registration;

@Controller
@RequestMapping("/registration/author")
public class RegistrationAuthorController {

	@Autowired
	private RegistrationService			registrationService;

	@Autowired
	private ConferenceService			conferenceService;

	@Autowired
	private AuthorService				authorService;

	@Autowired
	private CustomizableSystemService	customizableService;
	@Autowired
	private CreditCardService			creditCardService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createForm() {
		ModelAndView result;

		final Collection<Conference> conferences = this.conferenceService.getIncomingConferences();
		final Collection<Conference> conferencesAuthor = this.conferenceService.getAllConferenceByAuthor();
		conferences.removeAll(conferencesAuthor);
		final Collection<String> marcas = this.customizableService.getBrandNameCreditCard();
		final Registration registration = new Registration();
		final Collection<CreditCard> myCreditCards = this.creditCardService.getCreditCardByAuthor();

		result = new ModelAndView("registration/create");
		result.addObject("conferences", conferences);
		result.addObject("marcas", marcas);
		result.addObject("registration", registration);
		result.addObject("myCreditCards", myCreditCards);

		return result;
	}
}


package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import domain.Conference;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;


	//No logeado
	@RequestMapping(value = "/allConference", method = RequestMethod.GET)
	public ModelAndView allConference() {
		final ModelAndView result;
		final Collection<Conference> conferences;

		conferences = this.conferenceService.findAll();

		result = new ModelAndView("conference/allConference");

		//Futuras
		result.addObject("conferences", conferences);
		//Pasadas
		result.addObject("conferences", conferences);
		//Presentes
		result.addObject("conferences", conferences);

		return result;
	}
}

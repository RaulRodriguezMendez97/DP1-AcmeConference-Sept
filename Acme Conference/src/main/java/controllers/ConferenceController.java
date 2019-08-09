
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import domain.Conference;
import forms.ConferenceFinderForm;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Conference> conferences;

		conferences = this.conferenceService.getConferencesInSaveMode();
		final String lang = LocaleContextHolder.getLocale().getLanguage();
		final ConferenceFinderForm finder = new ConferenceFinderForm();
		finder.create();

		result = new ModelAndView("conference/list");

		result.addObject("conferences", conferences);
		result.addObject("lang", lang);
		result.addObject("finder", finder);

		return result;
	}

	@RequestMapping(value = "/incoming", method = RequestMethod.GET)
	public ModelAndView incomingConferences() {
		final ModelAndView result;
		final Collection<Conference> conferences;

		conferences = this.conferenceService.getIncomingConferences();
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("conference/catalogue");

		result.addObject("conferences", conferences);
		result.addObject("lang", lang);
		result.addObject("uri", "conference/incoming.do");

		return result;
	}

	@RequestMapping(value = "/running", method = RequestMethod.GET)
	public ModelAndView runningConferences() {
		final ModelAndView result;
		final Collection<Conference> conferences;

		conferences = this.conferenceService.getActivesConferences();

		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("conference/catalogue");

		result.addObject("conferences", conferences);
		result.addObject("lang", lang);
		result.addObject("uri", "conference/running.do");

		return result;
	}

	@RequestMapping(value = "/past", method = RequestMethod.GET)
	public ModelAndView pastConferences() {
		final ModelAndView result;
		final Collection<Conference> conferences;

		conferences = this.conferenceService.getPastConferences();

		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("conference/catalogue");

		result.addObject("conferences", conferences);
		result.addObject("lang", lang);
		result.addObject("uri", "conference/past.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer idConference) {
		ModelAndView result;

		try {
			final Conference conference;

			conference = this.conferenceService.findOne(idConference);
			final String lang = LocaleContextHolder.getLocale().getLanguage();
			Assert.notNull(conference);
			result = new ModelAndView("conference/show");

			result.addObject("conference", conference);
			result.addObject("lang", lang);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../");
		}

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(final ConferenceFinderForm finder, final BindingResult binding) {
		ModelAndView result;
		final Collection<Conference> conferences;

		conferences = this.conferenceService.getConferencesByFinder(finder.getKeyWord());
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("conference/list");
		result.addObject("conferences", conferences);
		result.addObject("finder", finder);
		result.addObject("lang", lang);

		return result;
	}
}


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

import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.ConferenceService;
import domain.Actor;
import domain.Conference;

@Controller
@RequestMapping("/conference/administrator")
public class ConferenceAdministratorController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private ActorService		actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;

		final UserAccount user = LoginService.getPrincipal();
		final Actor a = this.actorService.getActorByUserAccount(user.getId());

		final Collection<Conference> conferences = this.conferenceService.findAll();

		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("conference/list");
		result.addObject("conferences", conferences);
		result.addObject("lang", lang);
		return result;

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer idConference) {
		ModelAndView result;

		try {
			final Conference conference = this.conferenceService.findOne(idConference);

			final String lang = LocaleContextHolder.getLocale().getLanguage();
			result = new ModelAndView("conference/show");
			result.addObject("conference", conference);
			result.addObject("lang", lang);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;

		final Conference conference = this.conferenceService.create();

		result = new ModelAndView("conference/edit");
		result.addObject("conference", conference);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer idConference) {
		ModelAndView result;

		try {
			final Conference conference = this.conferenceService.findOne(idConference);
			Assert.isTrue(conference.getFinalMode() == 0);
			result = new ModelAndView("conference/edit");
			result.addObject("conference", conference);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(final Conference conference, final BindingResult binding) {

		ModelAndView result;
		try {
			final Conference c = this.conferenceService.reconstruct(conference, binding);
			if (!binding.hasErrors()) {
				this.conferenceService.save(c);
				result = new ModelAndView("redirect:list.do");
			} else {
				result = new ModelAndView("conference/edit");
				result.addObject("conference", conference);
			}
		} catch (final Exception e) {
			result = new ModelAndView("conference/edit");
			result.addObject("conference", conference);
			result.addObject("exception", e);
		}
		return result;
	}

	@RequestMapping(value = "/submission-last-5", method = RequestMethod.GET)
	public ModelAndView listConferencesSubmissionLast5Days() {
		final ModelAndView result;

		final Collection<Conference> conferences = this.conferenceService.getConferencesSubmissionLast5Days();

		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("conference/catalogue");
		result.addObject("conferences", conferences);
		result.addObject("lang", lang);
		result.addObject("uri", "conference/administrator/submission-last-5.do");
		return result;

	}

	@RequestMapping(value = "/notification-less-5", method = RequestMethod.GET)
	public ModelAndView listConferencesNotificationLess5Days() {
		final ModelAndView result;

		final Collection<Conference> conferences = this.conferenceService.getConferencesNotificationLess5Days();

		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("conference/catalogue");
		result.addObject("conferences", conferences);
		result.addObject("lang", lang);
		result.addObject("uri", "conference/administrator/notification-less-5.do");
		return result;

	}

	@RequestMapping(value = "/camera-ready-less-5", method = RequestMethod.GET)
	public ModelAndView listConferencesCameraReadyLess5Days() {
		final ModelAndView result;

		final Collection<Conference> conferences = this.conferenceService.getConferencesCameraLess5Days();

		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("conference/catalogue");
		result.addObject("conferences", conferences);
		result.addObject("lang", lang);
		result.addObject("uri", "conference/administrator/camera-ready-less-5.do");
		return result;

	}

	@RequestMapping(value = "/start-less-5", method = RequestMethod.GET)
	public ModelAndView listConferencesStartLess5Days() {
		final ModelAndView result;

		final Collection<Conference> conferences = this.conferenceService.getConferencesStartLess5Days();

		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("conference/catalogue");
		result.addObject("conferences", conferences);
		result.addObject("lang", lang);
		result.addObject("uri", "conference/administrator/start-less-5.do");
		return result;

	}
}

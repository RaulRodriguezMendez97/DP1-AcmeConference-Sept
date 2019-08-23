
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import services.PresentationService;
import domain.Conference;
import domain.Presentation;

@Controller
@RequestMapping("/presentation/administrator")
public class PresentationAdministratorController extends AbstractController {

	@Autowired
	private PresentationService	presentationService;
	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Presentation> presentations = this.presentationService.findAll();
		result = new ModelAndView("presentation/list");
		result.addObject("presentations", presentations);
		return result;

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer presentationId) {
		ModelAndView result;
		try {
			final Presentation presentation = this.presentationService.findOne(presentationId);
			Assert.notNull(presentation);
			result = new ModelAndView("presentation/show");
			result.addObject("presentation", presentation);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final Presentation presentation = this.presentationService.create();
		result = new ModelAndView("presentation/edit");
		result.addObject("presentation", presentation);
		result.addObject("conferences", this.conferenceService.getFutureAndDraftModeConferences());
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer presentationId) {
		ModelAndView result;
		try {
			final Presentation presentation = this.presentationService.findOne(presentationId);
			Assert.notNull(presentation);
			final Collection<Conference> conferences = this.conferenceService.getFutureAndDraftModeConferences();
			Assert.isTrue(conferences.contains(presentation.getConference()));
			result = new ModelAndView("presentation/edit");
			result.addObject("presentation", presentation);
			result.addObject("conferences", conferences);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(final Presentation presentation, final BindingResult binding) {
		ModelAndView result;
		try {
			final Presentation p = this.presentationService.reconstruct(presentation, binding);
			final Collection<Conference> conferences = this.conferenceService.getFutureAndDraftModeConferences();
			if (!binding.hasErrors()) {
				Assert.isTrue(conferences.contains(presentation.getConference()));
				this.presentationService.save(p);
				result = new ModelAndView("redirect:list.do");
			} else {
				result = new ModelAndView("presentation/edit");
				result.addObject("presentation", presentation);
				result.addObject("conferences", conferences);
			}
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Presentation presentation) {
		ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.getFutureAndDraftModeConferences();
		try {
			final Presentation p = this.presentationService.findOne(presentation.getId());
			Assert.notNull(p);
			Assert.isTrue(conferences.contains(p.getConference()));
			this.presentationService.delete(p);
			result = new ModelAndView("redirect:list.do");

		} catch (final Exception e) {
			result = new ModelAndView("presentation/edit");
			result.addObject("presentation", presentation);
			result.addObject("conferences", conferences);
		}
		return result;

	}

}

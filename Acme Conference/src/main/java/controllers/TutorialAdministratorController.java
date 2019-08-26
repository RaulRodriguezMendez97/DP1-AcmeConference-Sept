
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
import services.SectionService;
import services.TutorialService;
import domain.Conference;
import domain.Tutorial;

@Controller
@RequestMapping("/tutorial/administrator")
public class TutorialAdministratorController extends AbstractController {

	@Autowired
	private TutorialService		tutorialService;
	@Autowired
	private SectionService		sectionService;
	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Tutorial> tutorials = this.tutorialService.findAll();
		result = new ModelAndView("tutorial/list");
		result.addObject("tutorials", tutorials);
		return result;

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer tutorialId) {
		ModelAndView result;
		try {
			final Tutorial tutorial = this.tutorialService.findOne(tutorialId);
			Assert.notNull(tutorial);
			result = new ModelAndView("tutorial/show");
			result.addObject("tutorial", tutorial);
			result.addObject("sections", this.sectionService.getSectionsByTutorial(tutorialId));
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final Tutorial tutorial = this.tutorialService.create();
		result = new ModelAndView("tutorial/edit");
		result.addObject("tutorial", tutorial);
		result.addObject("conferences", this.conferenceService.getFutureAndFinalModeConferences());
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer tutorialId) {
		ModelAndView result;
		try {
			final Tutorial tutorial = this.tutorialService.findOne(tutorialId);
			Assert.notNull(tutorial);
			final Collection<Conference> conferences = this.conferenceService.getFutureAndFinalModeConferences();
			Assert.isTrue(conferences.contains(tutorial.getConference()));
			result = new ModelAndView("tutorial/edit");
			result.addObject("tutorial", tutorial);
			result.addObject("conferences", conferences);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;
		try {
			final Tutorial t = this.tutorialService.reconstruct(tutorial, binding);
			final Collection<Conference> conferences = this.conferenceService.getFutureAndFinalModeConferences();
			if (!binding.hasErrors()) {
				Assert.isTrue(conferences.contains(tutorial.getConference()));
				this.tutorialService.save(t);
				result = new ModelAndView("redirect:list.do");
			} else {
				result = new ModelAndView("tutorial/edit");
				result.addObject("tutorial", tutorial);
				result.addObject("conferences", conferences);
			}
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Tutorial tutorial) {
		ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.getFutureAndFinalModeConferences();
		try {
			final Tutorial t = this.tutorialService.findOne(tutorial.getId());
			Assert.notNull(t);
			Assert.isTrue(conferences.contains(t.getConference()));
			this.sectionService.deleteAllSectionsByTutorial(tutorial.getId());
			this.tutorialService.delete(t);
			result = new ModelAndView("redirect:list.do");

		} catch (final Exception e) {
			result = new ModelAndView("tutorial/edit");
			result.addObject("tutorial", tutorial);
			result.addObject("conferences", conferences);
		}
		return result;

	}

}

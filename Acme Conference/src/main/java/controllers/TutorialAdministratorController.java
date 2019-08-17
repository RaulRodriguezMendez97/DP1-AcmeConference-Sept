
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
			result = new ModelAndView("redirect:.list.do");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final Tutorial tutorial = this.tutorialService.create();
		result = new ModelAndView("tutorial/edit");
		result.addObject("tutorial", tutorial);
		result.addObject("conferences", this.conferenceService.getConferencesInDraftMode());
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer tutorialId) {
		ModelAndView result;
		try {
			final Tutorial tutorial = this.tutorialService.findOne(tutorialId);
			Assert.notNull(tutorial);
			result = new ModelAndView("tutorial/edit");
			result.addObject("tutorial", tutorial);
			result.addObject("conferences", this.conferenceService.getConferencesInDraftMode());
		} catch (final Exception e) {
			result = new ModelAndView("redirect:.list.do");
		}
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;
		try {
			final Tutorial t = this.tutorialService.reconstruct(tutorial, binding);
			if (!binding.hasErrors()) {
				this.tutorialService.save(t);
				result = new ModelAndView("redirect:list.do");
			} else {
				result = new ModelAndView("tutorial/edit");
				result.addObject("tutorial", tutorial);
				result.addObject("conferences", this.conferenceService.getConferencesInDraftMode());
			}
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final Integer idTopic) {
		ModelAndView result;
		try {
			final Tutorial tutorial = this.tutorialService.findOne(idTopic);
			Assert.notNull(tutorial);
			this.tutorialService.delete(tutorial);
			result = new ModelAndView("redirect:list.do");

		} catch (final Exception e) {
			result = new ModelAndView("redirect:.list.do");

		}
		return result;

	}

}

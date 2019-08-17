
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
import domain.Section;
import domain.Tutorial;

@Controller
@RequestMapping("/section/administrator")
public class SectionAdministratorController extends AbstractController {

	@Autowired
	private TutorialService		tutorialService;
	@Autowired
	private SectionService		sectionService;
	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final Integer tutorialId) {
		ModelAndView result;
		try {
			final Tutorial tutorial = this.tutorialService.findOne(tutorialId);
			Assert.notNull(tutorial);
			final Collection<Section> sections = this.sectionService.getSectionsByTutorial(tutorialId);
			result = new ModelAndView("section/list");
			result.addObject("sections", sections);
			result.addObject("tutorialId", tutorialId);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer tutorialId) {
		ModelAndView result;
		try {
			final Tutorial tutorial = this.tutorialService.findOne(tutorialId);
			Assert.notNull(tutorial);
			final Section section = this.sectionService.create();
			result = new ModelAndView("section/edit");
			result.addObject("section", section);
			result.addObject("tutorialId", tutorialId);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer tutorialId, @RequestParam final Integer sectionId) {
		ModelAndView result;
		try {
			final Tutorial tutorial = this.tutorialService.findOne(tutorialId);
			final Section section = this.sectionService.findOne(sectionId);
			Assert.notNull(tutorial);
			Assert.notNull(section);
			final Collection<Conference> conferences = this.conferenceService.getFutureAndDraftModeConferences();
			Assert.isTrue(conferences.contains(tutorial.getConference()));
			result = new ModelAndView("section/edit");
			result.addObject("section", section);
			result.addObject("tutorialId", tutorialId);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/");
		}
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(final Section section, final BindingResult binding) {
		ModelAndView result;
		try {

			final Tutorial tutorial = section.getTutorial();
			final Collection<Conference> conferences = this.conferenceService.getFutureAndDraftModeConferences();
			Assert.isTrue(conferences.contains(tutorial.getConference()));
			final Section s = this.sectionService.reconstruct(section, binding);
			if (!binding.hasErrors()) {
				this.sectionService.save(s);
				result = new ModelAndView("redirect:list.do?tutorialId=" + tutorial.getId());
			} else {
				result = new ModelAndView("tutorial/edit");
				result.addObject("tutorial", tutorial.getId());
			}
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final Integer tutorialId, @RequestParam final Integer sectionId) {
		ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.getFutureAndDraftModeConferences();
		try {
			final Tutorial t = this.tutorialService.findOne(tutorialId);
			Assert.notNull(t);
			Assert.isTrue(conferences.contains(t.getConference()));
			this.tutorialService.delete(t);
			result = new ModelAndView("redirect:list.do?tutorialId=" + tutorialId);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/");
		}
		return result;

	}

}

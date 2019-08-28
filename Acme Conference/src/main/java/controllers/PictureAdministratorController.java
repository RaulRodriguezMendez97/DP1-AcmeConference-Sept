
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import services.PictureService;
import services.SectionService;
import services.TutorialService;
import domain.Conference;
import domain.Picture;
import domain.Section;
import domain.Tutorial;

@Controller
@RequestMapping("/picture/administrator")
public class PictureAdministratorController extends AbstractController {

	@Autowired
	private TutorialService		tutorialService;
	@Autowired
	private SectionService		sectionService;
	@Autowired
	private PictureService		pictureService;
	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final Integer tutorialId, @RequestParam final Integer sectionId, @RequestParam final Integer pictureId) {
		ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.getFutureAndFinalModeConferences();
		try {
			final Tutorial tutorial = this.tutorialService.findOne(tutorialId);
			Assert.notNull(tutorial);
			Assert.isTrue(conferences.contains(tutorial.getConference()));
			final Section section = this.sectionService.findOne(sectionId);
			Assert.notNull(section);
			final Picture picture = this.pictureService.findOne(pictureId);
			Assert.notNull(picture);
			Assert.isTrue(section.getPictures().contains(picture));
			section.getPictures().remove(picture);
			this.pictureService.delete(pictureId);
			result = new ModelAndView("redirect:/section/administrator/edit.do?tutorialId=" + tutorialId + "&sectionId=" + sectionId);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/tutorial/administrator/list.do");
		}
		return result;

	}
}

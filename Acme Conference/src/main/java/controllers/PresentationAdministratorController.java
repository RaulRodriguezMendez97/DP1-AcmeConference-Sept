
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CamaraReadyService;
import services.ConferenceService;
import services.PresentationService;
import domain.CamaraReady;
import domain.Conference;
import domain.Presentation;

@Controller
@RequestMapping("/presentation/administrator")
public class PresentationAdministratorController extends AbstractController {

	@Autowired
	private PresentationService	presentationService;
	@Autowired
	private ConferenceService	conferenceService;
	@Autowired
	private CamaraReadyService	cameraReadyService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String error) {
		final ModelAndView result;
		final Collection<Presentation> presentations = this.presentationService.findAllByAdmin();
		result = new ModelAndView("presentation/list");
		result.addObject("presentations", presentations);
		if (error != null)
			result.addObject("error", "error");
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
		result.addObject("conferences", this.conferenceService.getFutureAndFinalModeConferences());
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer presentationId) {
		ModelAndView result;
		try {
			final Presentation presentation = this.presentationService.findOne(presentationId);
			Assert.notNull(presentation);
			final Collection<Conference> conferences = this.conferenceService.getFutureAndFinalModeConferences();
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
			final Collection<Conference> conferences = this.conferenceService.getFutureAndFinalModeConferences();
			if (!binding.hasErrors()) {
				Assert.isTrue(conferences.contains(presentation.getConference()));
				final Collection<CamaraReady> cameras = this.cameraReadyService.getCameraReadyByConference(presentation.getConference().getId());
				Assert.isTrue(cameras.contains(presentation.getCamaraReady()));
				this.presentationService.save(p);
				result = new ModelAndView("redirect:list.do");
			} else {
				result = new ModelAndView("presentation/edit");
				result.addObject("presentation", presentation);
				result.addObject("conferences", conferences);
				result.addObject("error", "1");
			}
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do?error=error");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Presentation presentation) {
		ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.getFutureAndFinalModeConferences();
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
			result.addObject("error", "1");
		}
		return result;

	}

	@RequestMapping(value = "/camaraReadyList", method = RequestMethod.GET)
	public ResponseEntity<String> list(@RequestParam final int conferenceId) {
		try {
			Assert.isTrue(this.conferenceService.getFutureAndFinalModeConferences().contains(this.conferenceService.findOne(conferenceId)));
			final Collection<CamaraReady> p = this.cameraReadyService.getCameraReadyByConference(conferenceId);
			final List<CamaraReady> a = new ArrayList<CamaraReady>(p);
			String camaras = "";
			for (int x = 0; x < a.size(); x++)
				if (x == 0)
					camaras += a.get(x).getTitle() + ":" + a.get(x).getId();
				else
					camaras += ";" + a.get(x).getTitle() + ":" + a.get(x).getId();
			return new ResponseEntity<String>(camaras, HttpStatus.OK);
		} catch (final Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

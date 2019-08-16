
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CamaraReadyService;
import services.SubmissionService;
import domain.CamaraReady;
import domain.Submission;

@Controller
@RequestMapping("/camera-ready/author")
public class CamaraReadyAuthorController extends AbstractController {

	@Autowired
	private CamaraReadyService	camaraReadyService;

	@Autowired
	private SubmissionService	submissionService;

	@Autowired
	private ActorService		actorService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer idSubmission) {
		ModelAndView result;
		try {
			final String lang = LocaleContextHolder.getLocale().getLanguage();
			final CamaraReady camaraReady = this.camaraReadyService.getCameraReadyBySubmission(idSubmission);
			final Submission submission = this.submissionService.findOne(idSubmission);
			Assert.isTrue(submission.getStatus() == 2);

			result = new ModelAndView("camera-ready/show");
			result.addObject("camaraReady", camaraReady);
			result.addObject("submission", submission);
			result.addObject("lang", lang);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");
		}
		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer idSubmission) {
		ModelAndView result;
		try {

			final CamaraReady camaraReady = this.camaraReadyService.create();
			Assert.isTrue(idSubmission != null);

			result = new ModelAndView("camera-ready/edit");
			result.addObject("camaraReady", camaraReady);
			result.addObject("idSubmission", idSubmission);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");
		}
		return result;

	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer idCameraReady, @RequestParam final Integer idSubmission) {
		ModelAndView result;
		try {
			final CamaraReady camaraReady = this.camaraReadyService.findOne(idCameraReady);
			Assert.isTrue(idSubmission != null);
			Assert.isTrue(idCameraReady != null);

			result = new ModelAndView("camera-ready/edit");
			result.addObject("camaraReady", camaraReady);
			result.addObject("idSubmission", idSubmission);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");
		}
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@RequestParam final Integer idSubmission, final CamaraReady camaraReady, final BindingResult binding) {

		ModelAndView result;

		try {
			final CamaraReady c = this.camaraReadyService.reconstruct(camaraReady, idSubmission, binding);
			if (!binding.hasErrors()) {
				final CamaraReady saved = this.camaraReadyService.save(c);
				final Submission submission = this.submissionService.findOne(idSubmission);
				submission.setCamaraReady(saved);
				this.submissionService.saveAuthor(submission);
				result = new ModelAndView("redirect:show.do?idSubmission=" + idSubmission);
			} else {
				result = new ModelAndView("camera-ready/edit");
				result.addObject("camaraReady", camaraReady);
				result.addObject("idSubmission", idSubmission);
			}
		} catch (final IllegalArgumentException e) {
			result = new ModelAndView("camera-ready/edit");
			result.addObject("camaraReady", camaraReady);
			result.addObject("idSubmission", idSubmission);
			result.addObject("exception", e);
		}

		return result;
	}

}

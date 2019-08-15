
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

		final CamaraReady camaraReady = this.camaraReadyService.create();

		result = new ModelAndView("camera-ready/edit");
		result.addObject("camaraReady", camaraReady);
		result.addObject("idSubmission", idSubmission);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer idCameraReady, @RequestParam final Integer idSubmission) {
		ModelAndView result;

		final CamaraReady camaraReady = this.camaraReadyService.findOne(idCameraReady);

		result = new ModelAndView("camera-ready/edit");
		result.addObject("camaraReady", camaraReady);
		result.addObject("idSubmission", idSubmission);

		return result;

	}

}

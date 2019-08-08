
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CamaraReadyService;
import services.ConferenceService;
import services.ReviwedService;
import services.SubmissionService;
import domain.CamaraReady;
import domain.Conference;
import domain.Reviwed;
import domain.Submission;

@Controller
@RequestMapping("/submission/author")
public class SubmissionAuthorController extends AbstractController {

	@Autowired
	private SubmissionService	submissionService;
	@Autowired
	private ConferenceService	conferenceService;
	@Autowired
	private ReviwedService		reviwedService;
	@Autowired
	private CamaraReadyService	camaraReadyService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Submission> submissions = this.submissionService.findAll();

		result = new ModelAndView("submission/list");
		result.addObject("submissions", submissions);
		return result;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam final Integer submissionId) {
		ModelAndView result;
		//final Collection<String> vacio = new HashSet<>();
		try {
			final Submission submission = this.submissionService.findOne(submissionId);
			final Conference conference = this.conferenceService.findOne(submission.getConference().getId());
			final Reviwed reviwed = this.reviwedService.findOne(submission.getReviwed().getId());
			final CamaraReady camaraReady = this.camaraReadyService.findOne(submission.getCamaraReady().getId());
			Assert.notNull(submission);
			Assert.notNull(conference);
			Assert.notNull(reviwed);

			result = new ModelAndView("submission/detail");
			result.addObject("submission", submission);
			result.addObject("conference", conference);
			result.addObject("reviwed", reviwed);
			result.addObject("camaraReady", camaraReady);
			//result.addObject("vacio", vacio);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../");
		}
		return result;
	}
}

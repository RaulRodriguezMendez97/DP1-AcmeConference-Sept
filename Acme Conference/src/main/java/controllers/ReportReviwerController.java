
package controllers;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReportService;
import services.ReviwerService;
import services.SubmissionService;
import domain.Report;

@Controller
@RequestMapping("/report/reviwer")
public class ReportReviwerController extends AbstractController {

	@Autowired
	private ReportService		reportService;
	@Autowired
	private SubmissionService	submissionService;
	@Autowired
	private ReviwerService		reviwerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int submissionId) {
		final ModelAndView result;
		//		final UserAccount userAccount = LoginService.getPrincipal();
		//		final Reviwer reviwer = this.reviwerService.getReviwerByUserAccount(userAccount.getId());
		final Collection<Report> reports = this.reportService.getReportsBySubmission(submissionId);
		result = new ModelAndView("report/list");
		result.addObject("reports", reports);
		result.addObject("submissionId", submissionId);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int submissionId) {
		final ModelAndView result;
		//		final UserAccount userAccount = LoginService.getPrincipal();
		//		final Reviwer reviwer = this.reviwerService.getReviwerByUserAccount(userAccount.getId());
		//		final Collection<Submission> submissions = this.submissionService.getSubmissionByReviwer(reviwer.getId());
		final Report report = this.reportService.create();

		result = new ModelAndView("report/edit");
		result.addObject("report", report);
		//result.addObject("submissions", submissions);
		result.addObject("submissionId", submissionId);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int submissionId, @RequestParam final int reportId) {
		ModelAndView result;
		try {
			final Report report = this.reportService.findOne(reportId);
			//			final UserAccount userAccount = LoginService.getPrincipal();
			//			final Reviwer reviwer = this.reviwerService.getReviwerByUserAccount(userAccount.getId());
			//			final Collection<Submission> submissions = this.submissionService.getSubmissionByReviwer(reviwer.getId());

			Assert.notNull(report);
			//Assert.notNull(submissions);

			result = new ModelAndView("report/edit");
			result.addObject("report", report);
			result.addObject("submissionId", submissionId);
			//			result.addObject("submissions", submissions);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do?submissionId=" + submissionId);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@RequestParam final int submissionId, final Report report, final BindingResult binding) {
		ModelAndView result;
		try {
			final Report o = this.reportService.reconstruct(report, submissionId, binding);
			if (!binding.hasErrors()) {
				this.reportService.save(o);
				result = new ModelAndView("redirect:list.do?submissionId=" + submissionId);
			} else {
				//				final UserAccount userAccount = LoginService.getPrincipal();
				//				final Reviwer reviwer = this.reviwerService.getReviwerByUserAccount(userAccount.getId());
				//				final Collection<Submission> submissions = this.submissionService.getSubmissionByReviwer(reviwer.getId());
				result = new ModelAndView("report/edit");
				result.addObject("report", report);
				//result.addObject("submissions", submissions);
				result.addObject("submissionId", submissionId);
			}
		} catch (final ValidationException opps) {
			//			final UserAccount userAccount = LoginService.getPrincipal();
			//			final Reviwer reviwer = this.reviwerService.getReviwerByUserAccount(userAccount.getId());
			//			final Collection<Submission> submissions = this.submissionService.getSubmissionByReviwer(reviwer.getId());
			result = new ModelAndView("report/edit");
			result.addObject("report", report);
			//result.addObject("submissions", submissions);
			result.addObject("submissionId", submissionId);
		} catch (final Exception e) {
			//			final UserAccount userAccount = LoginService.getPrincipal();
			//			final Reviwer reviwer = this.reviwerService.getReviwerByUserAccount(userAccount.getId());
			//			final Collection<Submission> submissions = this.submissionService.getSubmissionByReviwer(reviwer.getId());
			result = new ModelAndView("report/edit");
			result.addObject("exception", e);
			result.addObject("report", report);
			//result.addObject("submissions", submissions);
			result.addObject("submissionId", submissionId);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam final int submissionId, final Report report, final BindingResult binding) {
		ModelAndView result;
		Report o;
		try {
			o = this.reportService.reconstruct(report, submissionId, binding);
			if (!binding.hasErrors()) {
				this.reportService.delete(o);
				result = new ModelAndView("redirect:list.do?submissionId=" + submissionId);
			} else {
				//				final UserAccount userAccount = LoginService.getPrincipal();
				//				final Reviwer reviwer = this.reviwerService.getReviwerByUserAccount(userAccount.getId());
				//				final Collection<Submission> submissions = this.submissionService.getSubmissionByReviwer(reviwer.getId());
				result = new ModelAndView("report/edit");
				result.addObject("report", report);
				//				result.addObject("submissions", submissions);
				result.addObject("submissionId", submissionId);
			}
		} catch (final Exception e) {
			//			final UserAccount userAccount = LoginService.getPrincipal();
			//			final Reviwer reviwer = this.reviwerService.getReviwerByUserAccount(userAccount.getId());
			//			final Collection<Submission> submissions = this.submissionService.getSubmissionByReviwer(reviwer.getId());
			result = new ModelAndView("report/edit");
			result.addObject("exception", e);
			result.addObject("report", report);
			//			result.addObject("submissions", submissions);
			result.addObject("submissionId", submissionId);
		}
		return result;
	}

}

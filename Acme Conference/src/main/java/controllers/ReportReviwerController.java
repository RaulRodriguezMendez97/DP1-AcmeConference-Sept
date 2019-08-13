
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
import domain.Report;

@Controller
@RequestMapping("/report/reviwer")
public class ReportReviwerController extends AbstractController {

	@Autowired
	private ReportService	reportService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final Integer submissionId) {
		final ModelAndView result;
		final Collection<Report> reports = this.reportService.getReportsBySubmission(submissionId);

		result = new ModelAndView("report/list");
		result.addObject("reports", reports);
		result.addObject("submissionId", submissionId);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer submissionId) {
		final ModelAndView result;
		final Report report;

		report = this.reportService.create(/* submissionId */);
		Assert.notNull(report);

		result = new ModelAndView("report/edit");
		result.addObject("report", report);
		result.addObject("submissionId", submissionId);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@RequestParam final Integer submissionId, final Report report, final BindingResult binding) {
		ModelAndView result;
		Report o;
		try {
			o = this.reportService.reconstruct(report, submissionId, binding);
			if (!binding.hasErrors()) {
				this.reportService.save(o);
				result = new ModelAndView("redirect:list.do?submissionId=" + submissionId);
			} else {
				result = new ModelAndView("report/edit");
				//result = new ModelAndView("redirect:edit.do?submissionId=" + submissionId);
				result.addObject("submissionId", submissionId);
				result.addObject("report", report);
			}
		} catch (final ValidationException opps) {
			result = new ModelAndView("report/edit");
			result.addObject("submissionId", submissionId);
			result.addObject("report", report);
		} catch (final Exception e) {
			result = new ModelAndView("report/edit");
			result.addObject("exception", e);
			result.addObject("submissionId", submissionId);
			result.addObject("report", report);
		}
		return result;
	}

}

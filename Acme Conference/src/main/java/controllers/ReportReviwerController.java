
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		return result;
	}

}

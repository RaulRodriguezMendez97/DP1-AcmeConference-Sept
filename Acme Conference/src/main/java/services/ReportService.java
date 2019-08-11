
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ReportRepository;
import repositories.ReviwerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Report;
import domain.Submission;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;
	@Autowired
	private ReviwerRepository	reviwerRepository;


	public Report create() {
		final Report res = new Report();
		final UserAccount user = LoginService.getPrincipal();
		res.setComments(new HashSet<String>());
		res.setDecision(0);
		res.setEadabilityScore(0);
		res.setOriginalityScore(0);
		res.setQualityScore(0);
		res.setReviwer(this.reviwerRepository.getReviwerByUserAccount(user.getId()));
		res.setSubmission(new Submission());
		return res;
	}

	public Report findOne(final Integer reportId) {
		return this.reportRepository.findOne(reportId);
	}

	public Collection<Report> findAll() {
		return this.reportRepository.findAll();
	}

	public Collection<Report> getReportsBySubmission(final Integer submissionId) {
		return this.reportRepository.getReportsBySubmission(submissionId);
	}

}


package services;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ReportRepository;
import repositories.ReviwerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Report;
import domain.Reviwer;
import domain.Submission;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;
	@Autowired
	private ReviwerRepository	reviwerRepository;
	@Autowired
	private Validator			validator;
	@Autowired
	private SubmissionService	submissionService;
	@Autowired
	private ReviwerService		reviwerService;


	public Report create() {
		final Report res = new Report();
		final UserAccount user = LoginService.getPrincipal();
		final Reviwer reviwer = this.reviwerRepository.getReviwerByUserAccount(user.getId());
		res.setComment("");
		res.setDecision(0);
		res.setEadabilityScore(0);
		res.setOriginalityScore(0);
		res.setQualityScore(0);
		res.setReviwer(reviwer);
		res.setSubmission(new Submission());
		return res;
	}

	public Report findOne(final int reportId) {
		final Report report = this.reportRepository.findOne(reportId);
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("REVIWER"));
		Assert.isTrue(report.getReviwer().equals(this.reviwerRepository.getReviwerByUserAccount(userAccount.getId())));
		return report;
	}

	public Collection<Report> findAll() {
		return this.reportRepository.findAll();
	}

	public Collection<Report> getReportsBySubmission(final Integer submissionId) {
		final Submission submission = this.submissionService.findOne(submissionId);
		final UserAccount userAccount = LoginService.getPrincipal();
		final Reviwer reviwer = this.reviwerService.getReviwerByUserAccount(userAccount.getId());
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("REVIWER"));
		Assert.isTrue(submission.getReviwers().contains(reviwer));
		return this.reportRepository.getReportsBySubmission(submissionId);
	}

	public Report save(final Report report) {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("REVIWER"));
		Assert.isTrue(report.getReviwer().equals(this.reviwerRepository.getReviwerByUserAccount(userAccount.getId())));
		final Report reportSave = this.reportRepository.save(report);
		//		final Submission submission = this.submissionService.findOne(reportSave.getSubmission().getId());
		//		submission.getReviwers().add(e)
		//		submission.setReviwers(reviwers)
		return reportSave;
	}

	public void delete(final Report report) {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("REVIWER"));
		Assert.isTrue(report.getReviwer().equals(this.reviwerRepository.getReviwerByUserAccount(userAccount.getId())));
		this.reportRepository.delete(report);
	}

	public Report reconstruct(final Report report, final int submissionId, final BindingResult binding) {
		Report res;
		if (report.getId() == 0) {
			res = report;
			final UserAccount user = LoginService.getPrincipal();
			final Reviwer reviwer = this.reviwerRepository.getReviwerByUserAccount(user.getId());
			final Submission submission = this.submissionService.findOneReviwer(submissionId);
			res.setReviwer(reviwer);
			res.setSubmission(submission);
			res.setDecision(0);
			this.validator.validate(res, binding);
		} else {
			res = this.reportRepository.findOne(report.getId());
			final Report o = new Report();
			o.setId(res.getId());
			o.setVersion(res.getVersion());
			o.setReviwer(res.getReviwer());
			o.setSubmission(res.getSubmission());

			o.setComment(report.getComment());
			o.setDecision(report.getDecision());
			o.setEadabilityScore(report.getEadabilityScore());
			o.setOriginalityScore(report.getOriginalityScore());
			o.setQualityScore(report.getQualityScore());
			this.validator.validate(o, binding);

			if (binding.hasErrors())
				throw new ValidationException();

			res = o;
		}
		return res;
	}

	public Collection<Report> getReportsByReviwer(final int reviwerId) {
		return this.reportRepository.getReportsByReviwer(reviwerId);
	}

	//Numero de report con decicion aceptada, dada una submission
	public int getReportsDecicionAceptadaBySubmission(final int submissionId) {
		//final Submission submission = this.submissionService.findOne(submissionId);
		final UserAccount userAccount = LoginService.getPrincipal();
		//final Administrator admin = this.administratorService.getAdministratorByUserAccount(userAccount.getId());
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		//Assert.isTrue(submission.getConference().getAdmin().equals(admin));
		return this.reportRepository.getReportsConDecisionIgualAceptadaBySubmission(submissionId);
	}

	//Numero de report con decicion rechazada, dada una submission
	public int getReportsDecicionRechazadaBySubmission(final int submissionId) {
		//final Submission submission = this.submissionService.findOne(submissionId);
		final UserAccount userAccount = LoginService.getPrincipal();
		//final Administrator admin = this.administratorService.getAdministratorByUserAccount(userAccount.getId());
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		//Assert.isTrue(submission.getConference().getAdmin().equals(admin));
		return this.reportRepository.getReportsConDecisionIgualRechazadaBySubmission(submissionId);
	}

	//Numero de report con decicion border-line, dada una submission
	public int getReportsDecicionBorderLineBySubmission(final int submissionId) {
		//final Submission submission = this.submissionService.findOne(submissionId);
		final UserAccount userAccount = LoginService.getPrincipal();
		//final Administrator admin = this.administratorService.getAdministratorByUserAccount(userAccount.getId());
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		//Assert.isTrue(submission.getConference().getAdmin().equals(admin));
		return this.reportRepository.getReportsConDecisionIgualBorderLineBySubmission(submissionId);
	}

}

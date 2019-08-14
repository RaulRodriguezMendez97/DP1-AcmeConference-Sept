
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SubmissionRepository;
import security.LoginService;
import security.UserAccount;
import domain.Author;
import domain.Submission;
import forms.SubmissionReviwedForm;

@Service
@Transactional
public class SubmissionService {

	@Autowired
	private SubmissionRepository	submissionRepository;
	@Autowired
	private AuthorService			authorService;
	@Autowired
	private Validator				validator;


	//	public Submission create() {
	//		final Submission submission = new Submission();
	//		final UserAccount user = LoginService.getPrincipal();
	//		final Author a = this.authorService.getAuthorByUserAccount(user.getId());
	//
	//		submission.setTicker("");
	//		submission.setStatus(0);
	//		submission.setAuthor(a);
	//		submission.setCamaraReady(null);
	//		submission.setConference(new Conference());
	//		submission.setMoment(new Date());
	//		submission.setReviwed(new Reviwed());
	//		submission.setReviwers(new HashSet<Reviwer>());
	//		return submission;
	//	}

	public Submission findOne(final int submissionId) {
		final Submission submission = this.submissionRepository.findOne(submissionId);
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
		Assert.isTrue(submission.getAuthor().equals(this.authorService.getAuthorByUserAccount(userAccount.getId())));
		return submission;
	}
	public Collection<Submission> findAll() {
		return this.submissionRepository.findAll();
	}

	public Submission save(final Submission submission) {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
		Assert.isTrue(submission.getAuthor().equals(this.authorService.getAuthorByUserAccount(userAccount.getId())));
		if (submission.getId() == 0)
			Assert.isTrue(submission.getStatus() == 0);
		final Submission submissionSave = this.submissionRepository.save(submission);
		return submissionSave;
	}

	public Collection<Submission> getSubmissionByAuthor(final Integer authorId) {
		return this.submissionRepository.getSubmissionByAuthor(authorId);
	}

	//Status igual a 0 (under review)
	public Collection<Submission> getSubmissionByAdministratorStatus0(final Integer adminId) {
		return this.submissionRepository.getSubmissionByAdministratorStatus0(adminId);
	}
	//Status igual a 1 (rejected)
	public Collection<Submission> getSubmissionByAdministratorStatus1(final Integer adminId) {
		return this.submissionRepository.getSubmissionByAdministratorStatus1(adminId);
	}
	//Status igual a 2 (accepted)
	public Collection<Submission> getSubmissionByAdministratorStatus2(final Integer adminId) {
		return this.submissionRepository.getSubmissionByAdministratorStatus2(adminId);
	}

	public Collection<Submission> getSubmissionByReviwer(final Integer reviwerId) {
		return this.submissionRepository.getSubmissionByReviwers(reviwerId);
	}

	//RECONSTRUCT

	public Submission reconstruct(final SubmissionReviwedForm submissionReviwedForm, final BindingResult binding) {
		final Submission res = new Submission();
		if (submissionReviwedForm.getId() == 0) {

			final UserAccount user = LoginService.getPrincipal();
			final Author a = this.authorService.getAuthorByUserAccount(user.getId());
			res.setAuthor(a);

			res.setId(submissionReviwedForm.getId());
			res.setVersion(submissionReviwedForm.getVersion());
			res.setConference(submissionReviwedForm.getConference());
			res.setMoment(new Date());
			res.setStatus(0);
			res.setTicker(SubmissionService.generarTicker());
			res.setReviwed(submissionReviwedForm.getReviwed());
			res.setCamaraReady(submissionReviwedForm.getCamaraReady());
			res.setReviwers(submissionReviwedForm.getReviwers());
			this.validator.validate(res, binding);
			//		} else {
			//			res = this.submissionRepository.findOne(submissionReviwedForm.getId());
			//			final Submission p = new Submission();
			//			p.setId(res.getId());
			//			p.setVersion(res.getVersion());
			//			p.setMoment(res.getMoment());
			//			p.setAuthor(res.getAuthor());
			//			p.setConference(submissionReviwedForm.getConference());
			//			p.setCamaraReady(res.getCamaraReady());
			//			p.setReviwers(res.getReviwers());
			//			p.setStatus(res.getStatus());
			//			p.setTicker(res.getTicker());
			//			reviwed.setSummary(submissionReviwedForm.getSummary());
			//			reviwed.setTitle(submissionReviwedForm.getTitle());
			//			reviwed.setUrlDocument(submissionReviwedForm.getUrlDocument());
			//			p.setReviwed(reviwed);
			//
			//			this.validator.validate(p, binding);
			//			res = p;
		}
		return res;
	}

	//TICKER
	public static String generarTicker() {
		final int tamLetras = 3;
		final int tam = 4;
		String d = "";
		final String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (int i = 0; i < tamLetras; i++) {
			final Integer random = (int) (Math.floor(Math.random() * letras.length()) % letras.length());
			d = d + letras.charAt(random);
		}

		String ticker = d + "-";
		final String a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (int i = 0; i < tam; i++) {
			final Integer random = (int) (Math.floor(Math.random() * a.length()) % a.length());
			ticker = ticker + a.charAt(random);
		}

		return ticker;

	}

}

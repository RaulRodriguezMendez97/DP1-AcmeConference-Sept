
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SubmissionRepository;
import domain.Author;
import domain.CamaraReady;
import domain.Conference;
import domain.Reviwed;
import domain.Reviwer;
import domain.Submission;

@Service
@Transactional
public class SubmissionService {

	@Autowired
	private SubmissionRepository	submissionRepository;


	public Submission create() {
		final Submission submission = new Submission();
		submission.setStatus(0);//UNDER REVIEW
		submission.setAuthor(new Author());
		submission.setCamaraReady(new CamaraReady());
		submission.setConference(new Conference());
		submission.setMoment(new Date());
		submission.setReviwed(new Reviwed());
		submission.setReviwers(new HashSet<Reviwer>());
		return submission;
	}

	public Submission findOne(final int submissionId) {
		return this.submissionRepository.findOne(submissionId);
	}

	public Collection<Submission> findAll() {
		return this.submissionRepository.findAll();
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

}

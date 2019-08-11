
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

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
	@Autowired
	private AuthorService			authorService;
	@Autowired
	private Validator				validator;


	public Submission create() {
		final Submission submission = new Submission();
		submission.setTicker(SubmissionService.generarTicker());
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

	public Collection<Submission> getSubmissionByReviwer(final Integer reviwerId) {
		return this.submissionRepository.getSubmissionByReviwers(reviwerId);
	}

	//RECONSTRUCT
	/*
	 * public Submission reconstruct(final Submission food, final BindingResult binding) {
	 * final Submission res;
	 * 
	 * if (food.getId() == 0) {
	 * res = food;
	 * 
	 * final UserAccount user = LoginService.getPrincipal();
	 * final Author a = this.authorService.getAuthorByUserAccount(user.getId());
	 * 
	 * food.setAuthor(a);
	 * food.setMoment(new Date());
	 * food.setTicker(SubmissionService.generarTicker());
	 * food.setStatus(0);
	 * 
	 * this.validator.validate(res, binding);
	 * return res;
	 * } else {
	 * res = this.submissionRepository.findOne(food.getId());
	 * final Submission copy = new Submission();
	 * copy.setAuthor(res.getAuthor());
	 * copy.setMoment(res.getMoment());
	 * copy.setId(res.getId());
	 * copy.setVersion(res.getVersion());
	 * copy.setTicker(res.getTicker());
	 * 
	 * copy.setName(food.getName())
	 * copy.setDescription(food.getDescription());
	 * copy.setPictures(food.getPictures());
	 * copy.setPrice(food.getPrice());
	 * copy.setType(food.getType());
	 * copy.setIngredients(food.getIngredients());
	 * 
	 * this.validator.validate(copy, binding);
	 * if (binding.hasErrors())
	 * throw new ValidationException();
	 * return copy;
	 * }
	 * }
	 */

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

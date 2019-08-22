
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SubmissionRepository;
import security.LoginService;
import security.UserAccount;
import domain.Author;
import domain.Reviwer;
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
	@Autowired
	private ReviwerService			reviwerService;
	@Autowired
	private MessageService			messageService;


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

	public Submission findOne(final Integer id) {
		return this.submissionRepository.findOne(id);
	}

	//FindOne si esta logueado como author
	public Submission findOneAuthor(final int submissionId) {
		final Submission submission = this.submissionRepository.findOne(submissionId);
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
		Assert.isTrue(submission.getAuthor().equals(this.authorService.getAuthorByUserAccount(userAccount.getId())));
		return submission;
	}

	//FindOne si esta logueado como Administrator
	public Submission findOneAdministrator(final int submissionId) {
		final Submission submission = this.submissionRepository.findOne(submissionId);
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		return submission;
	}

	//FindOne si esta logueado como Reviwer
	public Submission findOneReviwer(final int submissionId) {
		final Submission submission = this.submissionRepository.findOne(submissionId);
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("REVIWER"));
		return submission;
	}
	public Collection<Submission> findAll() {
		return this.submissionRepository.findAll();
	}

	public Submission saveAuthor(final Submission submission) {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
		Assert.isTrue(submission.getAuthor().equals(this.authorService.getAuthorByUserAccount(userAccount.getId())));
		if (submission.getId() == 0)
			Assert.isTrue(submission.getStatus() == 0);
		final Submission submissionSave = this.submissionRepository.save(submission);
		return submissionSave;
	}

	public Submission saveAdmin(final Submission submission) {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));

		if (submission.getStatus() == 2 || submission.getStatus() == 1)//Aceptada o rechazada
			Assert.isTrue(!submission.getReviwers().isEmpty());

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

	public Submission reconstructSubmissionAdministrator(final Submission submission, final BindingResult binding) {
		Submission res = null;
		if (submission.getId() == 0) {

		} else {
			res = this.submissionRepository.findOne(submission.getId());
			final Submission p = new Submission();
			p.setId(res.getId());
			p.setVersion(res.getVersion());
			p.setMoment(res.getMoment());
			p.setAuthor(res.getAuthor());
			p.setConference(res.getConference());
			p.setCamaraReady(res.getCamaraReady());
			p.setTicker(res.getTicker());
			p.setReviwed(res.getReviwed());

			if (!res.getReviwers().isEmpty()) {
				p.setStatus(submission.getStatus());
				p.setReviwers(res.getReviwers());
			}

			if (res.getReviwers().isEmpty() || res.getReviwers().equals(null)) {
				p.setStatus(res.getStatus());
				p.setReviwers(submission.getReviwers());
			}

			this.validator.validate(p, binding);
			if (binding.hasErrors())
				throw new ValidationException();
			res = p;
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

	private static List<String> trocear(final String cadena) {
		final List<String> palabras = new ArrayList<>();
		final String[] trozos = cadena.trim().split(" ");
		for (final String a : trozos)
			palabras.add(a);
		return palabras;
	}

	public Submission asignarAutomaticamenteReviwers(final Submission submission) {
		//Reviwers
		final List<Reviwer> reviwers = (List<Reviwer>) this.reviwerService.findAll();
		final Collection<Reviwer> rev = new HashSet<>();
		final List<String> palabrasTitulo = SubmissionService.trocear(submission.getConference().getTitle());
		final List<String> palabrasResumen = SubmissionService.trocear(submission.getConference().getSummary());
		Boolean res = false;
		Submission submissionSave = null;

		if (reviwers.size() >= 3) {
			for (int i = 1; i <= 3; i++) {
				final int tam = reviwers.size();
				final int a = (int) (Math.random() * tam - 1);
				final Reviwer aux = reviwers.get(a);

				for (final String keys : aux.getKeyWords())
					for (final String pt : palabrasTitulo)
						if (keys.equals(pt)) {
							rev.add(aux);
							reviwers.remove(aux);
							res = true;
							break;
						}

				if (res == false) {

					for (final String keys : aux.getKeyWords())
						for (final String pr : palabrasResumen)
							if (keys.equals(pr)) {
								rev.add(aux);
								reviwers.remove(aux);
								res = true;
								break;
							}

				} else
					break;
			}
			submission.setReviwers(reviwers);
			submissionSave = this.submissionRepository.save(submission);
		}
		return submissionSave;
	}

}


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
import domain.Administrator;
import domain.Author;
import domain.Conference;
import domain.Reviwed;
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
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private ReviwedService			reviwedService;
	@Autowired
	private ReportService			reportService;


	public Submission create() {
		final Submission submission = new Submission();
		final UserAccount user = LoginService.getPrincipal();
		final Author a = this.authorService.getAuthorByUserAccount(user.getId());
		final Reviwed reviwed = this.reviwedService.create();

		submission.setTicker(this.generarTicker());
		submission.setStatus(0);
		submission.setAuthor(a);
		submission.setCamaraReady(null);
		submission.setConference(new Conference());
		submission.setMoment(new Date());
		submission.setReviwed(reviwed);
		submission.setReviwers(new HashSet<Reviwer>());
		return submission;
	}

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
		final Administrator admin = this.administratorService.getAdministratorByUserAccount(userAccount.getId());
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		Assert.isTrue(submission.getConference().getAdmin().equals(admin));
		return submission;
	}

	//FindOne si esta logueado como Reviwer
	public Submission findOneReviwer(final int submissionId) {
		final Submission submission = this.submissionRepository.findOne(submissionId);
		final UserAccount userAccount = LoginService.getPrincipal();
		final Reviwer reviwer = this.reviwerService.getReviwerByUserAccount(userAccount.getId());
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("REVIWER"));
		Assert.isTrue(submission.getReviwers().contains(reviwer));

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

		if (submission.getStatus() == 2 || submission.getStatus() == 1) {//Aceptada o rechazada
			Assert.isTrue(!submission.getReviwers().isEmpty());
			this.messageService.sendMessageSubmission(submission);
		}

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
		final Submission res = this.create();
		final Reviwed reviwed = this.reviwedService.create();

		reviwed.setCoAuthors(submissionReviwedForm.getCoAuthors());
		reviwed.setSummary(submissionReviwedForm.getSummary());
		reviwed.setTitle(submissionReviwedForm.getTitle());
		reviwed.setUrlDocument(submissionReviwedForm.getUrlDocument());
		this.validator.validate(reviwed, binding);
		final Reviwed reviwedSave = this.reviwedService.save(reviwed);

		if (submissionReviwedForm.getId() == 0) {
			res.setConference(submissionReviwedForm.getConf());
			res.setReviwed(reviwedSave);
			this.validator.validate(res, binding);
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
	public String generarTicker() {
		final int tam = 4;
		String d = "";
		final UserAccount userAccount = LoginService.getPrincipal();
		final Author author = this.authorService.getAuthorByUserAccount(userAccount.getId());

		final char primerCaracterNombre = author.getName().charAt(0);
		final String primeraLetraNombre = primerCaracterNombre + "";

		final char primerCaracterApellido = author.getSurname().charAt(0);
		final String primeraLetraApellido = primerCaracterApellido + "";

		d = d + primeraLetraNombre.toUpperCase() + primeraLetraApellido.toUpperCase();
		if (author.getMiddleName().equals(""))
			d = d + "X";
		else {
			final char primerCaracterMiddleName = author.getMiddleName().charAt(0);
			final String primeraLetraMiddelName = primerCaracterMiddleName + "";
			d = d + primeraLetraMiddelName.toUpperCase();
		}

		String ticker = d + "-";
		final String a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (int i = 0; i < tam; i++) {
			final Integer random = (int) (Math.floor(Math.random() * a.length()) % a.length());
			ticker = ticker + a.charAt(random);
		}
		return ticker;
	}
	public List<String> trocear(final String cadena) {
		final List<String> palabras = new ArrayList<>();
		final String[] trozos = cadena.split(" ");
		for (final String a : trozos)
			palabras.add(a);
		return palabras;
	}

	//Asignar de forma automática los reviwers
	public Submission asignarAutomaticamenteReviwers(final Submission submission, final BindingResult binding) {
		//Reviwers adecuados
		final Collection<Reviwer> reviwers = this.reviwerService.findAll();
		final List<Reviwer> listaReviwers = this.reviwersAdecuados(reviwers, submission);

		List<Reviwer> rev = new ArrayList<Reviwer>();
		Submission submissionRes = null;

		if (listaReviwers.size() >= 3)
			for (int i = 1; i <= 3; i++) {
				final int tam = listaReviwers.size();
				final int a = (int) Math.floor(Math.random() * ((tam - 1) - 0 + 1) + 0);
				final Reviwer aux = listaReviwers.get(a);
				rev.add(aux);
				listaReviwers.remove(aux);
				submissionRes = this.submissionRepository.findOne(submission.getId());
				submissionRes.setReviwers(rev);
				this.validator.validate(submissionRes, binding);
			}
		else {
			rev = listaReviwers;
			submissionRes = this.submissionRepository.findOne(submission.getId());
			submissionRes.setReviwers(rev);
			this.validator.validate(submissionRes, binding);
		}
		return submissionRes;
	}

	//Cambiar el estado de una submission de forma automática.
	public Submission changeStatus(final Submission submission, final BindingResult binding) {
		Submission res = null;
		res = this.submissionRepository.findOne(submission.getId());

		if (this.reportService.getReportsDecicionAceptadaBySubmission(submission.getId()) >= this.reportService.getReportsDecicionRechazadaBySubmission(submission.getId()))
			res.setStatus(2);
		else if (this.reportService.getReportsDecicionRechazadaBySubmission(submission.getId()) > this.reportService.getReportsDecicionAceptadaBySubmission(submission.getId()))
			res.setStatus(1);
		else
			res.setStatus(2);

		this.validator.validate(res, binding);
		return res;

	}

	//Obtener reviwers adecuados
	public List<Reviwer> reviwersAdecuados(final Collection<Reviwer> reviwers, final Submission submission) {
		final Submission sub = this.findOne(submission.getId());
		final List<Reviwer> resultado = new ArrayList<>();
		for (final Reviwer r : reviwers)
			for (final String a : this.trocear(sub.getConference().getTitle()))
				if (!(r.getKeyWords().contains(a))) {
					for (final String b : this.trocear(sub.getConference().getSummary()))
						if (r.getKeyWords().contains(b)) {
							resultado.add(r);
							break;
						}
					break;
				} else {
					resultado.add(r);
					break;
				}
		return resultado;
	}

}

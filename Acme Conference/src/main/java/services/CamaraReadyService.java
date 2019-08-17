
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CamaraReadyRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Author;
import domain.CamaraReady;
import domain.Submission;

@Service
@Transactional
public class CamaraReadyService {

	@Autowired
	private CamaraReadyRepository	camaraReadyRepository;

	@Autowired
	private SubmissionService		submissionService;

	@Autowired
	private Validator				validator;

	@Autowired
	private ActorService			actorService;


	public CamaraReady create() {
		final CamaraReady res = new CamaraReady();

		res.setSummary("");
		res.setTitle("");
		res.setUrlDocument("");
		res.setAuthor(new Author());
		res.setCoAuthors(new HashSet<Author>());

		return res;
	}

	public Collection<CamaraReady> findAll() {
		return this.camaraReadyRepository.findAll();
	}

	public CamaraReady findOne(final int camaraReadyId) {
		return this.camaraReadyRepository.findOne(camaraReadyId);
	}

	public CamaraReady getCameraReadyBySubmission(final Integer id) {
		return this.camaraReadyRepository.getCameraReadyBySubmission(id);
	}

	public CamaraReady save(final CamaraReady camaraReady) {
		final UserAccount user = LoginService.getPrincipal();
		final Actor a = this.actorService.getActorByUserAccount(user.getId());
		Assert.isTrue(camaraReady.getAuthor().equals(a) || camaraReady.getCoAuthors().contains(a));

		final CamaraReady saved = this.camaraReadyRepository.save(camaraReady);
		return saved;
	}

	public CamaraReady reconstruct(final CamaraReady camaraReady, final Integer submissionId, final BindingResult binding) {
		CamaraReady res;

		if (camaraReady.getId() == 0) {
			res = camaraReady;

			final UserAccount user = LoginService.getPrincipal();
			final Actor a = this.actorService.getActorByUserAccount(user.getId());

			camaraReady.setAuthor((Author) a);

			final Submission submission = this.submissionService.findOne(submissionId);
			Assert.isTrue(submission.getConference().getCameraDeadline().after(new Date()));

			this.validator.validate(res, binding);

			return res;

		} else {
			res = this.camaraReadyRepository.findOne(camaraReady.getId());
			final CamaraReady copy = new CamaraReady();

			final UserAccount userAccount = LoginService.getPrincipal();
			final Actor a = this.actorService.getActorByUserAccount(userAccount.getId());

			if (a.equals(res.getAuthor())) {
				copy.setId(res.getId());
				copy.setVersion(res.getVersion());
				copy.setAuthor(res.getAuthor());

				copy.setTitle(camaraReady.getTitle());
				copy.setSummary(camaraReady.getSummary());
				copy.setUrlDocument(camaraReady.getUrlDocument());
				copy.setCoAuthors(camaraReady.getCoAuthors());

			} else if (res.getCoAuthors().contains(a)) {
				copy.setId(res.getId());
				copy.setVersion(res.getVersion());
				copy.setAuthor(res.getAuthor());

				copy.setCoAuthors(camaraReady.getCoAuthors());
				copy.setTitle(camaraReady.getTitle());
				copy.setSummary(camaraReady.getSummary());
				copy.setUrlDocument(camaraReady.getUrlDocument());

				if (this.comparaListas(camaraReady.getCoAuthors(), res.getCoAuthors()) == false)
					binding.rejectValue("coAuthors", "NoCoAuthors");

			}

			final Submission submission = this.submissionService.findOne(submissionId);
			Assert.isTrue(submission.getConference().getCameraDeadline().after(new Date()));

			this.validator.validate(copy, binding);

			return copy;
		}
	}

	public Boolean comparaListas(final Collection<Author> main, final Collection<Author> secundary) {
		Boolean res = true;

		if (main.size() != secundary.size())
			res = false;

		if (res != false)
			for (final Author a : main)
				if (!secundary.contains(a)) {
					res = false;
					break;
				}
		return res;

	}
}

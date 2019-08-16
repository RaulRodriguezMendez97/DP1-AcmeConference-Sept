
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
		res.setCoAuthors(new ArrayList<Author>());

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
			copy.setId(res.getId());
			copy.setVersion(res.getVersion());
			copy.setAuthor(res.getAuthor());

			copy.setTitle(camaraReady.getTitle());
			copy.setSummary(camaraReady.getSummary());
			copy.setUrlDocument(camaraReady.getUrlDocument());
			copy.setCoAuthors(camaraReady.getCoAuthors());

			final Submission submission = this.submissionService.findOne(submissionId);
			Assert.isTrue(submission.getConference().getCameraDeadline().after(new Date()));

			this.validator.validate(copy, binding);

			return copy;
		}
	}
}

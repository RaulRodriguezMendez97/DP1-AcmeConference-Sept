
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ConferenceRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Author;
import domain.Conference;

@Service
@Transactional
public class ConferenceService {

	@Autowired
	private ConferenceRepository	conferenceRepository;

	@Autowired
	private ActorService			actorService;
	@Autowired
	private AuthorService			authorService;

	@Autowired
	private Validator				validator;


	public Conference create() {
		final Conference conference = new Conference();

		conference.setTitle("");
		conference.setAcronym("");
		conference.setVenue("");
		conference.setSubmissionDeadline(new Date());
		conference.setNotificacionDeadline(new Date());
		conference.setCameraDeadline(new Date());
		conference.setStartDate(new Date());
		conference.setEndDate(new Date());
		conference.setSummary("");
		conference.setFee(0);
		conference.setFinalMode(0);
		conference.setAdmin(new Administrator());

		return conference;
	}

	public Collection<Conference> findAll() {
		return this.conferenceRepository.findAll();
	}

	public Conference findOne(final Integer id) {
		return this.conferenceRepository.findOne(id);
	}

	public Collection<Conference> getConferencesByAdmin(final Integer idAdmin) {
		return this.conferenceRepository.getConferencesByAdmin(idAdmin);
	}

	public Collection<Conference> getConferencesInSaveMode() {
		return this.conferenceRepository.getConferencesInSaveMode();
	}

	public Collection<Conference> getConferencesByFinder(final String keyWord) {
		return this.conferenceRepository.getConferencesByFinder(keyWord);
	}

	public Collection<Conference> getActivesConferences() {
		return this.conferenceRepository.getActivesConferences();
	}

	public Collection<Conference> getIncomingConferences() {
		return this.conferenceRepository.getIncomingConferences();
	}

	public Collection<Conference> getPastConferences() {
		return this.conferenceRepository.getPastConferences();
	}

	public Collection<Conference> getConferencesSubmissionLast5Days() {
		return this.conferenceRepository.getConferencesSubmissionLast5Days();
	}

	public Collection<Conference> getConferencesNotificationLess5Days() {
		return this.conferenceRepository.getConferencesNotificationLess5Days();
	}

	public Collection<Conference> getConferencesCameraLess5Days() {
		return this.conferenceRepository.getConferencesCameraLess5Days();
	}

	public Collection<Conference> getConferencesStartLess5Days() {
		return this.conferenceRepository.getConferencesStartLess5Days();
	}

	public Collection<Conference> getConferencesSubmissionDeadlinePosteriorNow() {
		return this.conferenceRepository.getConferencesSubmissionDeadLinePosteriorNow();
	}
	public Conference save(final Conference conference) {

		if (conference.getId() != 0) {
			final UserAccount user = LoginService.getPrincipal();
			final Administrator admin = (Administrator) this.actorService.getActorByUserAccount(user.getId());
			Assert.isTrue(conference.getAdmin() == admin);

			final Conference old = this.conferenceRepository.findOne(conference.getId());
			Assert.isTrue(old.getFinalMode() != 1);
		}

		final Conference saved = this.conferenceRepository.save(conference);
		return saved;
	}

	public Conference reconstruct(final Conference conference, final BindingResult binding) {
		Conference res;

		if (conference.getId() == 0) {
			res = conference;

			final UserAccount user = LoginService.getPrincipal();
			final Administrator admin = (Administrator) this.actorService.getActorByUserAccount(user.getId());

			conference.setAdmin(admin);

			//BEFORE
			if (conference.getSubmissionDeadline() != null)
				if (conference.getSubmissionDeadline().before(new Date()))
					binding.rejectValue("submissionDeadline", "FutureDate");

			if (conference.getSubmissionDeadline() != null && conference.getNotificacionDeadline() != null)
				if (!conference.getSubmissionDeadline().before(conference.getNotificacionDeadline()))
					binding.rejectValue("notificacionDeadline", "NoDateNotification");

			if (conference.getCameraDeadline() != null && conference.getNotificacionDeadline() != null)
				if (!conference.getNotificacionDeadline().before(conference.getCameraDeadline()))
					binding.rejectValue("cameraDeadline", "NoDateCamera");

			if (conference.getCameraDeadline() != null && conference.getStartDate() != null)
				if (!conference.getCameraDeadline().before(conference.getStartDate()))
					binding.rejectValue("startDate", "NoDateStart");

			if (conference.getEndDate() != null && conference.getStartDate() != null)

				if (!conference.getStartDate().before(conference.getEndDate()))
					binding.rejectValue("endDate", "NoDateEnd");

			this.validator.validate(res, binding);
			return res;

		} else {
			res = this.conferenceRepository.findOne(conference.getId());
			final Conference copy = new Conference();
			copy.setId(res.getId());
			copy.setVersion(res.getVersion());
			copy.setAdmin(res.getAdmin());

			copy.setTitle(conference.getTitle());
			copy.setAcronym(conference.getAcronym());
			copy.setVenue(conference.getVenue());
			copy.setSubmissionDeadline(conference.getSubmissionDeadline());
			copy.setNotificacionDeadline(conference.getNotificacionDeadline());
			copy.setCameraDeadline(conference.getCameraDeadline());
			copy.setStartDate(conference.getStartDate());
			copy.setEndDate(conference.getEndDate());
			copy.setSummary(conference.getSummary());
			copy.setFee(conference.getFee());
			copy.setFinalMode(conference.getFinalMode());

			//BEFORE
			if (conference.getSubmissionDeadline() != null)
				if (conference.getSubmissionDeadline().before(new Date()))
					binding.rejectValue("submissionDeadline", "FutureDate");

			if (conference.getSubmissionDeadline() != null && conference.getNotificacionDeadline() != null)
				if (!conference.getSubmissionDeadline().before(conference.getNotificacionDeadline()))
					binding.rejectValue("notificacionDeadline", "NoDateNotification");

			if (conference.getCameraDeadline() != null && conference.getNotificacionDeadline() != null)
				if (!conference.getNotificacionDeadline().before(conference.getCameraDeadline()))
					binding.rejectValue("cameraDeadline", "NoDateCamera");

			if (conference.getCameraDeadline() != null && conference.getStartDate() != null)
				if (!conference.getCameraDeadline().before(conference.getStartDate()))
					binding.rejectValue("startDate", "NoDateStart");

			if (conference.getEndDate() != null && conference.getStartDate() != null)

				if (!conference.getStartDate().before(conference.getEndDate()))
					binding.rejectValue("endDate", "NoDateEnd");

			this.validator.validate(copy, binding);

			return copy;
		}

	}

	public Collection<Conference> getConferencesInDraftMode() {
		return this.conferenceRepository.getConferencesInDraftMode();
	}

	public Collection<Conference> getFutureAndDraftModeConferences() {
		return this.conferenceRepository.getFutureAndDraftModeConferences();
	}

	public Collection<Conference> getAllConferenceByAuthor() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(user.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
		final Author author = this.authorService.getAuthorByUserAccount(user.getId());
		return this.conferenceRepository.getAllConferenceByAuthor(author.getId());
	}

	//DASHBOARD

	public List<Object[]> getAvgMinMaxDesvSubmissionsByConference() {
		return this.conferenceRepository.getAvgMinMaxDesvSubmissionsByConference();
	}

	public List<Object[]> getAvgMinMaxDesvRegistrationByConference() {
		return this.conferenceRepository.getAvgMinMaxDesvRegistrationByConference();
	}

	public List<Object[]> getAvgMinMaxDesvFeesByConference() {
		return this.conferenceRepository.getAvgMinMaxDesvFeesByConference();
	}

	public List<Object[]> getAvgMinMaxDesvDaysByConference() {
		return this.conferenceRepository.getAvgMinMaxDesvDaysByConference();
	}

}

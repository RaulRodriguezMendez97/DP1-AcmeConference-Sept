
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PresentationRepository;
import security.LoginService;
import domain.Administrator;
import domain.CamaraReady;
import domain.Conference;
import domain.Presentation;

@Service
@Transactional
public class PresentationService {

	@Autowired
	private PresentationRepository	presentationRepository;
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private CamaraReadyService		cameraReadyService;
	@Autowired
	private Validator				validator;


	public Presentation create() {
		final Presentation presentation = new Presentation();
		presentation.setTitle("");
		presentation.setSpeaker("");
		presentation.setDuration(0);
		presentation.setSchedule(new Date());
		presentation.setRoom("");
		presentation.setSummary("");
		presentation.setAttachments(new HashSet<String>());
		presentation.setConference(new Conference());
		presentation.setCamaraReady(new CamaraReady());
		return presentation;
	}

	public Collection<Presentation> findAll() {
		return this.presentationRepository.findAll();
	}

	public Collection<Presentation> findAllByAdmin() {
		final int userAccountId = LoginService.getPrincipal().getId();
		final Administrator a = this.administratorService.getAdministratorByUserAccount(userAccountId);
		return this.presentationRepository.findAllByAdmin(a.getId());
	}

	public Presentation findOne(final Integer id) {
		return this.presentationRepository.findOne(id);
	}

	public Presentation save(final Presentation presentation) {
		final Presentation saved = this.presentationRepository.save(presentation);
		return saved;
	}

	public Presentation reconstruct(final Presentation presentation, final BindingResult binding) {
		Presentation res;

		if (presentation.getId() == 0) {
			res = presentation;
			this.validator.validate(res, binding);
			final Collection<CamaraReady> usedCameras = this.cameraReadyService.getUsedCamerasByAdmin();
			if (usedCameras.contains(presentation.getCamaraReady()))
				binding.rejectValue("camaraReady", "presentation.camaraReady.used");
			if (usedCameras.contains(presentation.getCamaraReady()))
				binding.rejectValue("camaraReady", "presentation.camaraReady.used");
			if (presentation.getSchedule() != null && presentation.getSchedule().before(presentation.getConference().getStartDate()))
				binding.rejectValue("schedule", "presentation.startDate");
			if (presentation.getSchedule() != null && presentation.getSchedule().after(presentation.getConference().getEndDate()))
				binding.rejectValue("schedule", "presentation.endDate");

		} else {
			res = this.presentationRepository.findOne(presentation.getId());
			final Presentation copy = new Presentation();
			copy.setId(res.getId());
			copy.setVersion(res.getVersion());
			copy.setTitle(presentation.getTitle());
			copy.setSpeaker(presentation.getSpeaker());
			copy.setDuration(presentation.getDuration());
			copy.setSchedule(presentation.getSchedule());
			copy.setRoom(presentation.getRoom());
			copy.setSummary(presentation.getSummary());
			copy.setAttachments(presentation.getAttachments());
			copy.setConference(presentation.getConference());
			copy.setCamaraReady(presentation.getCamaraReady());
			this.validator.validate(copy, binding);

			final Collection<CamaraReady> usedCameras = this.cameraReadyService.getUsedCamerasByAdmin();
			if (usedCameras.contains(presentation.getCamaraReady()) && !res.getCamaraReady().equals(presentation.getCamaraReady()))
				binding.rejectValue("camaraReady", "presentation.camaraReady.used");
			if (presentation.getSchedule() != null && presentation.getSchedule().before(presentation.getConference().getStartDate()))
				binding.rejectValue("schedule", "presentation.startDate");
			if (presentation.getSchedule() != null && presentation.getSchedule().after(presentation.getConference().getEndDate()))
				binding.rejectValue("schedule", "presentation.endDate");
			res = copy;
		}
		return res;

	}

	public void delete(final Presentation presentation) {
		this.presentationRepository.delete(presentation.getId());
	}
}

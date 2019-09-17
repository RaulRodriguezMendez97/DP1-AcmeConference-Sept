
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PanelRepository;
import security.LoginService;
import domain.Administrator;
import domain.Conference;
import domain.Panel;

@Service
@Transactional
public class PanelService {

	@Autowired
	private PanelRepository			panelRepository;
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private Validator				validator;


	public Panel create() {
		final Panel panel = new Panel();
		panel.setTitle("");
		panel.setSpeaker("");
		panel.setDuration(0);
		panel.setSchedule(new Date());
		panel.setRoom("");
		panel.setSummary("");
		panel.setAttachments(new HashSet<String>());
		panel.setConference(new Conference());
		return panel;
	}

	public Collection<Panel> findAll() {
		return this.panelRepository.findAll();
	}

	public Collection<Panel> findAllByAdmin() {
		final int userAccountId = LoginService.getPrincipal().getId();
		final Administrator a = this.administratorService.getAdministratorByUserAccount(userAccountId);
		return this.panelRepository.findAllByAdmin(a.getId());
	}

	public Panel findOne(final Integer id) {
		return this.panelRepository.findOne(id);
	}

	public Panel save(final Panel panel) {
		final Panel saved = this.panelRepository.save(panel);
		return saved;
	}

	public Panel reconstruct(final Panel panel, final BindingResult binding) {
		Panel res;

		if (panel.getId() == 0) {
			res = panel;
			if (panel.getSchedule() != null && panel.getSchedule().before(panel.getConference().getStartDate()))
				binding.rejectValue("schedule", "presentation.startDate");
			if (panel.getSchedule() != null && panel.getSchedule().after(panel.getConference().getEndDate()))
				binding.rejectValue("schedule", "presentation.endDate");
			this.validator.validate(res, binding);

		} else {
			res = this.panelRepository.findOne(panel.getId());
			final Panel copy = new Panel();
			copy.setId(res.getId());
			copy.setVersion(res.getVersion());
			copy.setTitle(panel.getTitle());
			copy.setSpeaker(panel.getSpeaker());
			copy.setDuration(panel.getDuration());
			copy.setSchedule(panel.getSchedule());
			copy.setRoom(panel.getRoom());
			copy.setSummary(panel.getSummary());
			copy.setAttachments(panel.getAttachments());
			copy.setConference(panel.getConference());

			if (panel.getSchedule() != null && panel.getSchedule().before(panel.getConference().getStartDate()))
				binding.rejectValue("schedule", "presentation.startDate");
			if (panel.getSchedule() != null && panel.getSchedule().after(panel.getConference().getEndDate()))
				binding.rejectValue("schedule", "presentation.endDate");
			this.validator.validate(copy, binding);

			res = copy;
		}
		return res;

	}

	public void delete(final Panel panel) {
		this.panelRepository.delete(panel.getId());
	}

}

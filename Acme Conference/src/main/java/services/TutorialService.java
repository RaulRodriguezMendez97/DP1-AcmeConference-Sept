
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.TutorialRepository;
import domain.Conference;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository	tutorialRepository;
	@Autowired
	private Validator			validator;


	public Tutorial create() {
		final Tutorial tutorial = new Tutorial();
		tutorial.setTitle("");
		tutorial.setSpeaker("");
		tutorial.setDuration(0);
		tutorial.setSchedule(new Date());
		tutorial.setRoom("");
		tutorial.setSummary("");
		tutorial.setAttachments(new HashSet<String>());
		tutorial.setConference(new Conference());
		return tutorial;
	}

	public Collection<Tutorial> findAll() {
		return this.tutorialRepository.findAll();
	}

	public Tutorial findOne(final Integer id) {
		return this.tutorialRepository.findOne(id);
	}

	public Tutorial save(final Tutorial tutorial) {
		final Tutorial saved = this.tutorialRepository.save(tutorial);
		return saved;
	}

	public Tutorial reconstruct(final Tutorial tutorial, final BindingResult binding) {
		Tutorial res;

		if (tutorial.getId() == 0) {
			res = tutorial;
			this.validator.validate(res, binding);

		} else {
			res = this.tutorialRepository.findOne(tutorial.getId());
			final Tutorial copy = new Tutorial();
			copy.setId(res.getId());
			copy.setVersion(res.getVersion());
			copy.setTitle(tutorial.getTitle());
			copy.setSpeaker(tutorial.getSpeaker());
			copy.setDuration(tutorial.getDuration());
			copy.setSchedule(tutorial.getSchedule());
			copy.setRoom(tutorial.getRoom());
			copy.setSummary(tutorial.getSummary());
			copy.setAttachments(tutorial.getAttachments());
			copy.setConference(tutorial.getConference());
			this.validator.validate(copy, binding);

			res = copy;
		}
		return res;

	}

	public void delete(final Tutorial tutorial) {
		this.tutorialRepository.delete(tutorial.getId());
	}
}

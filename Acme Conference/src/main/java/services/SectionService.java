
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SectionRepository;
import domain.Section;
import domain.Tutorial;

@Service
@Transactional
public class SectionService {

	@Autowired
	private SectionRepository	sectionRepository;
	@Autowired
	private Validator			validator;


	public Section create() {
		final Section section = new Section();
		section.setSummary("");
		section.setTitle("");
		section.setTutorial(new Tutorial());
		return section;
	}

	public Collection<Section> findAll() {
		return this.sectionRepository.findAll();
	}

	public Section findOne(final Integer id) {
		return this.sectionRepository.findOne(id);
	}

	public Section save(final Section section) {
		final Section saved = this.sectionRepository.save(section);
		return saved;
	}

	public Section reconstruct(final Section section, final BindingResult binding) {
		Section res;

		if (section.getId() == 0) {
			res = section;
			this.validator.validate(res, binding);

		} else {
			res = this.sectionRepository.findOne(section.getId());
			final Section copy = new Section();
			copy.setId(res.getId());
			copy.setVersion(res.getVersion());
			copy.setSummary(section.getSummary());
			copy.setTitle(section.getTitle());
			copy.setTutorial(section.getTutorial());
			this.validator.validate(copy, binding);

			res = copy;
		}
		return res;

	}

}

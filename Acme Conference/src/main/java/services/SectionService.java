
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SectionRepository;
import domain.Picture;
import domain.Section;
import domain.Tutorial;
import forms.SectionPictureForm;

@Service
@Transactional
public class SectionService {

	@Autowired
	private PictureService		pictureService;
	@Autowired
	private SectionRepository	sectionRepository;
	@Autowired
	private Validator			validator;


	public Section create() {
		final Section section = new Section();
		section.setSummary("");
		section.setTitle("");
		section.setTutorial(new Tutorial());
		section.setPictures(new HashSet<Picture>());
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

	public Section reconstruct(final SectionPictureForm sectionPictureForm, final BindingResult binding) {
		Section res = new Section();

		if (sectionPictureForm.getId() == 0) {
			res.setSummary(sectionPictureForm.getSummary());
			res.setTitle(sectionPictureForm.getTitle());
			res.setTutorial(sectionPictureForm.getTutorial());
			res.setPictures(new HashSet<Picture>());
			this.validator.validate(res, binding);

		} else {
			res = this.sectionRepository.findOne(sectionPictureForm.getId());
			final Section copy = new Section();
			copy.setId(res.getId());
			copy.setVersion(res.getVersion());
			copy.setSummary(sectionPictureForm.getSummary());
			copy.setTitle(sectionPictureForm.getTitle());
			copy.setTutorial(res.getTutorial());
			copy.setPictures(res.getPictures());
			this.validator.validate(copy, binding);

			res = copy;
		}

		if (sectionPictureForm.getPicture() != "") {
			Picture p = new Picture();
			p.setUrlPicture(sectionPictureForm.getPicture());
			p = this.pictureService.reconstruct(p, binding);
			if (binding.hasFieldErrors("urlPicture"))
				binding.rejectValue("picture", "section.NotValidURL");
		}
		return res;

	}
	public void delete(final int sectionId) {
		this.sectionRepository.delete(sectionId);
	}

	public Collection<Section> getSectionsByTutorial(final int tutorialId) {
		return this.sectionRepository.getSectionsByTutorial(tutorialId);
	}

	public void deleteAllSectionsByTutorial(final int tutorialId) {
		final Collection<Section> sections = this.getSectionsByTutorial(tutorialId);
		for (final Section s : sections)
			this.sectionRepository.delete(s.getId());
	}
}

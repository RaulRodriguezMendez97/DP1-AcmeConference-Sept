
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PictureRepository;
import domain.Picture;
import domain.Section;

@Service
@Transactional
public class PictureService {

	@Autowired
	private PictureRepository	pictureRepository;
	@Autowired
	private Validator			validator;


	public Picture create() {
		final Picture picture = new Picture();
		picture.setUrlPicture("");
		picture.setSection(new Section());
		return picture;
	}

	public Collection<Picture> findAll() {
		return this.pictureRepository.findAll();
	}

	public Picture findOne(final Integer id) {
		return this.pictureRepository.findOne(id);
	}

	public Picture save(final Picture picture) {
		final Picture saved = this.pictureRepository.save(picture);
		return saved;
	}

	public Picture reconstruct(final Picture picture, final BindingResult binding) {
		Picture res;

		if (picture.getId() == 0) {
			res = picture;
			this.validator.validate(res, binding);

		} else {
			res = this.pictureRepository.findOne(picture.getId());
			final Picture copy = new Picture();
			copy.setId(res.getId());
			copy.setVersion(res.getVersion());
			copy.setUrlPicture(picture.getUrlPicture());
			copy.setSection(picture.getSection());
			this.validator.validate(copy, binding);

			res = copy;
		}
		return res;

	}

	public void delete(final Picture picture) {
		this.pictureRepository.delete(picture.getId());
	}

}

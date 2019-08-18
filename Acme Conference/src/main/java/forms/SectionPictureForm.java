
package forms;

import org.hibernate.validator.constraints.URL;

import domain.Section;

public class SectionPictureForm extends Section {

	private String	picture;


	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public SectionPictureForm create() {
		final SectionPictureForm res = new SectionPictureForm();

		res.setPicture("");
		res.setSummary("");
		res.setTitle("");
		return res;
	}

}

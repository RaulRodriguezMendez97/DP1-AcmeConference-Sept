
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Picture extends DomainEntity {

	private String	urlPicture;
	private Section	section;


	@ManyToOne(optional = false)
	@Valid
	@NotNull
	public Section getSection() {
		return this.section;
	}

	public void setSection(final Section section) {
		this.section = section;
	}

	@URL
	@NotBlank
	@NotNull
	public String getUrlPicture() {
		return this.urlPicture;
	}

	public void setUrlPicture(final String pic) {
		this.urlPicture = pic;
	}

}

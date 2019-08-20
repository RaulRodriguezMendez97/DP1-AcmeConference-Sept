
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Section extends DomainEntity {

	private String				title;
	private String				summary;
	private Tutorial			tutorial;
	private Collection<Picture>	pictures;


	@ManyToOne(optional = false)
	@Valid
	@NotNull
	public Tutorial getTutorial() {
		return this.tutorial;
	}

	public void setTutorial(final Tutorial tutorial) {
		this.tutorial = tutorial;
	}

	@NotBlank
	@NotNull
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@NotBlank
	@NotNull
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String tit) {
		this.title = tit;
	}

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Valid
	@NotNull
	public Collection<Picture> getPictures() {
		return this.pictures;
	}

	public void setPictures(final Collection<Picture> pictures) {
		this.pictures = pictures;
	}

}

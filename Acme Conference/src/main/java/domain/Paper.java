
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Paper extends DomainEntity {

	private String	title;
	private String	summary;
	private String	urlDocument;


	@URL
	@NotNull
	@NotBlank
	public String getUrlDocument() {
		return this.urlDocument;
	}

	public void setUrlDocument(final String urlDocument) {
		this.urlDocument = urlDocument;
	}

	@NotNull
	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@NotBlank
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

}

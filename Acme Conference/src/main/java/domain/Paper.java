
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Paper extends DomainEntity {

	private String				title;
	private String				summary;
	private String				urlDocument;

	private Author				author;
	private Collection<Author>	coAuthors;


	@URL
	@NotNull
	@NotBlank
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getUrlDocument() {
		return this.urlDocument;
	}

	public void setUrlDocument(final String urlDocument) {
		this.urlDocument = urlDocument;
	}

	@NotNull
	@NotBlank
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@NotBlank
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(final Author author) {
		this.author = author;
	}

	@ManyToMany
	@Valid
	@NotNull
	public Collection<Author> getCoAuthors() {
		return this.coAuthors;
	}

	public void setCoAuthors(final Collection<Author> coAuthors) {
		this.coAuthors = coAuthors;
	}

}

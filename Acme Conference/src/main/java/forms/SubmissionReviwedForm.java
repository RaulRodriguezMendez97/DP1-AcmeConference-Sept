
package forms;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import domain.Author;
import domain.Conference;
import domain.DomainEntity;

public class SubmissionReviwedForm extends DomainEntity {

	private String				title;
	private String				summary;
	private String				urlDocument;
	private Collection<Author>	coAuthors;
	private Conference			conf;


	@NotNull
	@Valid
	public Conference getConf() {
		return this.conf;
	}

	public void setConf(final Conference conf) {
		this.conf = conf;
	}

	@Valid
	public Collection<Author> getCoAuthors() {
		return this.coAuthors;
	}

	public void setCoAuthors(final Collection<Author> coAuthors) {
		this.coAuthors = coAuthors;
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

	@URL
	@NotNull
	@NotBlank
	public String getUrlDocument() {
		return this.urlDocument;
	}

	public void setUrlDocument(final String urlDocument) {
		this.urlDocument = urlDocument;
	}

	public SubmissionReviwedForm create() {
		final SubmissionReviwedForm res = new SubmissionReviwedForm();

		//Reviwed
		res.setSummary("");
		res.setTitle("");
		res.setUrlDocument("");
		res.setCoAuthors(new HashSet<Author>());

		//Submission
		res.setConf(new Conference());

		return res;
	}

}

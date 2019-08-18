
package forms;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import domain.Author;
import domain.CamaraReady;
import domain.Conference;
import domain.Reviwed;
import domain.Reviwer;
import domain.Submission;

public class SubmissionReviwedForm extends Submission {

	private String				title;
	private String				summary;
	private String				urlDocument;
	private Collection<Author>	coAuthors;


	@Valid
	@ManyToMany
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
		res.setConference(new Conference());
		res.setStatus(0);
		res.setMoment(new Date());
		res.setReviwers(new HashSet<Reviwer>());
		res.setCamaraReady(new CamaraReady());
		res.setTicker("");
		res.setAuthor(new Author());
		res.setReviwed(new Reviwed());
		return res;
	}

}

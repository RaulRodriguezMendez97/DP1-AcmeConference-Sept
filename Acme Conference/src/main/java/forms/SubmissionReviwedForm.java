
package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import domain.Conference;
import domain.Submission;

public class SubmissionReviwedForm extends Submission {

	private String		title;
	private String		summary;
	private String		urlDocument;
	private Conference	conference;


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

	@Override
	@NotNull
	@Valid
	public Conference getConference() {
		return this.conference;
	}

	@Override
	public void setConference(final Conference conference) {
		this.conference = conference;
	}

	public SubmissionReviwedForm create() {
		final SubmissionReviwedForm res = new SubmissionReviwedForm();

		res.setSummary("");
		res.setTitle("");
		res.setConference(new Conference());
		res.setUrlDocument("");

		return res;
	}

}

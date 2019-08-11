
package forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import domain.Conference;
import domain.DomainEntity;
import domain.Topic;

public class MessageBroadcastForm extends DomainEntity {

	private String		subject;
	private String		body;
	private Topic		topic;
	private Conference	conference;


	@NotBlank
	@NotNull
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotBlank
	@NotNull
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@NotNull
	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(final Topic topic) {
		this.topic = topic;
	}

	public Conference getConference() {
		return this.conference;
	}

	public void setConference(final Conference conference) {
		this.conference = conference;
	}

	public MessageBroadcastForm create() {
		final MessageBroadcastForm res = new MessageBroadcastForm();

		res.setSubject("");
		res.setBody("");
		res.setConference(new Conference());
		res.setTopic(new Topic());

		return res;
	}

}

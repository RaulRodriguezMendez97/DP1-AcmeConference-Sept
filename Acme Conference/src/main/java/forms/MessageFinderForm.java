
package forms;

import domain.Topic;

public class MessageFinderForm {

	private String	email;
	private Topic	topic;


	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(final Topic topic) {
		this.topic = topic;
	}

	public MessageFinderForm create() {
		final MessageFinderForm form = new MessageFinderForm();

		form.setEmail("");
		form.setTopic(new Topic());

		return form;
	}

}

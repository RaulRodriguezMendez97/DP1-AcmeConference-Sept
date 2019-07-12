
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	private Date	moment;
	private String	subject;
	private String	body;
	private int		priority;
	private String	tag;

	private Actor	sender;
	private Actor	receiver;
	private String	emailReceiver;


	@NotNull
	//@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@Range(min = 0, max = 2)
	public int getPriority() {
		return this.priority;
	}

	public void setPriority(final int priority) {
		this.priority = priority;
	}

	//OPTIONAL
	public String getTag() {
		return this.tag;
	}

	public void setTag(final String tag) {
		this.tag = tag;
	}
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}
	public void setSender(final Actor sender) {
		this.sender = sender;
	}
	@Valid
	@ManyToOne(optional = true)
	public Actor getReceiver() {
		return this.receiver;
	}

	public void setReceiver(final Actor receiver) {
		this.receiver = receiver;
	}

	@NotNull
	@NotBlank
	public String getEmailReceiver() {
		return this.emailReceiver;
	}

	public void setEmailReceiver(final String emailReceiver) {
		this.emailReceiver = emailReceiver;
	}

}


package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Message;
import domain.MessageBox;
import domain.Topic;

@Service
@Transactional
public class MessageService {

	@Autowired
	private Validator			validator;

	@Autowired
	private MessageRepository	messageRepository;

	@Autowired
	ActorService				actorService;

	@Autowired
	private MessageBoxService	messageBoxService;


	public Message create() {
		final Message message = new Message();

		message.setMoment(new Date());
		message.setSubject("");
		message.setBody("");
		message.setTopic(new Topic());
		message.setEmailReceiver("");
		message.setSender(new Actor());
		message.setReceiver(new Actor());

		return message;
	}

	public Collection<Message> findAll() {
		return this.messageRepository.findAll();
	}

	public Message findOne(final Integer id) {
		return this.messageRepository.findOne(id);

	}

	public Message save(final Message message) {
		final Message saved = this.messageRepository.save(message);

		return saved;
	}

	public void delete(final Message message) {
		final UserAccount user = LoginService.getPrincipal();
		final Actor a = this.actorService.getActorByUserAccount(user.getId());
		final MessageBox messageBox = this.messageBoxService.getMessageBoxByActor(a.getId());

		Assert.isTrue(message.getSender() == a || message.getReceiver() == a);

		final Collection<Message> mensajes = messageBox.getMessages();
		mensajes.remove(message);
		this.messageBoxService.save(messageBox);
	}

	//M�todos auxiliares

	public Message reconstruct(final Message message, final BindingResult binding) {
		Message res;
		res = message;

		final UserAccount user = LoginService.getPrincipal();
		final Actor sender = this.actorService.getActorByUserAccount(user.getId());
		final Actor receiver = this.actorService.getActorByEmail(message.getEmailReceiver());
		message.setSender(sender);
		message.setMoment(this.fechaSumada());
		message.setReceiver(receiver);

		if (message.getEmailReceiver() == "")
			binding.rejectValue("emailReceiver", "NoEmail");
		else if (receiver == null)
			binding.rejectValue("emailReceiver", "NotFound");

		this.validator.validate(res, binding);
		return res;
	}

	public void sendMessage(final Message message) {

		final Actor sender = message.getSender();
		final MessageBox messageBoxSender = this.messageBoxService.getMessageBoxByActor(sender.getId());

		final Actor receiver = message.getReceiver();
		final MessageBox messageBoxReceiver = this.messageBoxService.getMessageBoxByActor(receiver.getId());

		final Collection<Message> messageSender = messageBoxSender.getMessages();
		messageSender.add(message);
		this.messageBoxService.save(messageBoxSender);

		final Collection<Message> messageReceiver = messageBoxReceiver.getMessages();
		messageReceiver.add(message);
		this.messageBoxService.save(messageBoxReceiver);

	}

	public Date fechaSumada() {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); //tuFechaBase es un Date;
		calendar.add(Calendar.MINUTE, -2); //minutosASumar es int.
		//lo que m�s quieras sumar
		final Date fechaSalida = calendar.getTime(); //Y ya tienes la fecha sumada.
		return fechaSalida;
	}
}

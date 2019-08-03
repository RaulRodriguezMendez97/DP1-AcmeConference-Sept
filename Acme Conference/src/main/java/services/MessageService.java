
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Message;
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

	public Message reconstruct(final Message message, final BindingResult binding) {
		Message res;
		res = message;

		final UserAccount user = LoginService.getPrincipal();
		final Actor sender = this.actorService.getActorByUserAccount(user.getId());
		final Actor receiver = this.actorService.getActorByEmail(message.getEmailReceiver());
		message.setSender(sender);
		message.setMoment(new Date());
		message.setReceiver(receiver);

		this.validator.validate(res, binding);
		return res;
	}
}

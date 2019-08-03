
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.MessageBoxRepository;
import domain.Actor;
import domain.Message;
import domain.MessageBox;

@Service
@Transactional
public class MessageBoxService {

	@Autowired
	private MessageBoxRepository	messageBoxRepository;


	public MessageBox create() {
		final MessageBox messageBox = new MessageBox();

		messageBox.setActor(new Actor());
		messageBox.setMessages(new HashSet<Message>());

		return messageBox;
	}

	public Collection<MessageBox> findAll() {
		return this.messageBoxRepository.findAll();
	}

	public MessageBox findOne(final Integer id) {
		return this.messageBoxRepository.findOne(id);
	}

	public MessageBox getMessageBoxByActor(final Integer idActor) {
		return this.messageBoxRepository.getMessageBoxByActor(idActor);
	}

	public MessageBox save(final MessageBox messageBox) {
		MessageBox saved;
		saved = this.messageBoxRepository.save(messageBox);
		return saved;
	}
}

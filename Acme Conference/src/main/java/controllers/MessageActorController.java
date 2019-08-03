
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.MessageBoxService;
import services.MessageService;
import services.TopicService;
import domain.Actor;
import domain.Message;
import domain.MessageBox;
import domain.Topic;

@Controller
@RequestMapping("/message/actor")
public class MessageActorController extends AbstractController {

	@Autowired
	private MessageService		messageService;
	@Autowired
	private MessageBoxService	messageBoxService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private TopicService		topicService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final Actor a = this.actorService.getActorByUserAccount(user.getId());
		final MessageBox messageBox = this.messageBoxService.getMessageBoxByActor(a.getId());
		final Collection<Message> mensajes = messageBox.getMessages();

		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("mensajes/list");
		result.addObject("mensajes", mensajes);
		result.addObject("lang", lang);
		return result;

	}

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;

		final Message mensaje = this.messageService.create();
		final Collection<Topic> topics = this.topicService.findAll();

		result = new ModelAndView("mensaje/edit");
		result.addObject("mensaje", mensaje);
		result.addObject("topics", topics);
		return result;
	}
}

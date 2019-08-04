
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		result.addObject("message", mensaje);
		result.addObject("topics", topics);
		return result;
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(final Message mensaje, final BindingResult binding) {

		ModelAndView result;
		try {
			final Message m = this.messageService.reconstruct(mensaje, binding);
			if (!binding.hasErrors()) {
				final Message saved = this.messageService.save(m);
				this.messageService.sendMessage(saved);
				result = new ModelAndView("redirect:list.do");
			} else {
				result = new ModelAndView("mensaje/edit");
				final Collection<Topic> topics = this.topicService.findAll();
				result.addObject("message", mensaje);
				result.addObject("topics", topics);
			}
		} catch (final Exception e) {
			result = new ModelAndView("mensaje/edit");
			final Collection<Topic> topics = this.topicService.findAll();
			result.addObject("message", mensaje);
			result.addObject("topics", topics);
			result.addObject("exception", e);
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer idMessage) {
		ModelAndView result;
		try {
			final UserAccount user = LoginService.getPrincipal();
			final Actor a = this.actorService.getActorByUserAccount(user.getId());

			final Message mensaje = this.messageService.findOne(idMessage);
			final String lang = LocaleContextHolder.getLocale().getLanguage();

			Assert.isTrue(mensaje.getSender() == a || mensaje.getReceiver() == a);

			result = new ModelAndView("mensaje/show");
			result.addObject("mensaje", mensaje);
			result.addObject("lang", lang);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final Integer idMessage) {
		ModelAndView result;
		try {
			final UserAccount user = LoginService.getPrincipal();
			final Actor a = this.actorService.getActorByUserAccount(user.getId());

			final Message mensaje = this.messageService.findOne(idMessage);

			Assert.isTrue(mensaje.getSender() == a || mensaje.getReceiver() == a);
			this.messageService.delete(mensaje);
			result = new ModelAndView("redirect:list.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;

	}
}

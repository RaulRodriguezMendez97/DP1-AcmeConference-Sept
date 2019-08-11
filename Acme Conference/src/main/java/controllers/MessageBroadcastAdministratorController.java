
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ConferenceService;
import services.MessageBoxService;
import services.MessageService;
import services.TopicService;
import domain.Conference;
import domain.Message;
import domain.Topic;
import forms.MessageBroadcastForm;

@Controller
@RequestMapping("/message-broadcast/administrator")
public class MessageBroadcastAdministratorController extends AbstractController {

	@Autowired
	private ActorService		actorService;

	@Autowired
	private MessageService		messageService;

	@Autowired
	private MessageBoxService	messageBoxService;

	@Autowired
	private TopicService		topicService;

	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;

		final MessageBroadcastForm mensaje = new MessageBroadcastForm();
		mensaje.create();
		final Collection<Topic> topics = this.topicService.findAll();
		final Collection<Conference> conferences = this.conferenceService.getConferencesInSaveMode();

		result = new ModelAndView("mensaje/edit-broadcast");
		result.addObject("mensaje", mensaje);
		result.addObject("topics", topics);
		result.addObject("conferences", conferences);
		return result;
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@ModelAttribute("mensaje") final MessageBroadcastForm mensaje, final BindingResult binding) {
		ModelAndView result;

		final Message m = this.messageService.reconstructBroadcast(mensaje, binding);
		if (!binding.hasErrors()) {
			this.messageService.save(m);
			result = new ModelAndView("redirect:../../");
		} else {
			final Collection<Topic> topics = this.topicService.findAll();
			final Collection<Conference> conferences = this.conferenceService.getConferencesInSaveMode();
			result = new ModelAndView("mensaje/edit-broadcast");
			result.addObject("mensaje", mensaje);
			result.addObject("topics", topics);
			result.addObject("conferences", conferences);
		}

		return result;
	}

}


package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TopicService;
import domain.Topic;

@Controller
@RequestMapping("/topic/administrator")
public class TopicAdministratorController extends AbstractController {

	@Autowired
	private TopicService	topicService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Topic> topics = this.topicService.findAll();
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("topic/list");
		result.addObject("topics", topics);
		result.addObject("lang", lang);
		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;

		final Topic topic = this.topicService.create();

		result = new ModelAndView("topic/edit");
		result.addObject("topic", topic);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid @ModelAttribute("topic") final Topic topic, final BindingResult binding) {
		ModelAndView result;
		try {
			if (!binding.hasErrors()) {
				this.topicService.save(topic);
				result = new ModelAndView("redirect:list.do");
			} else {
				result = new ModelAndView("topic/edit");
				result.addObject("topic", topic);
			}
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");
		}
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer idTopic) {
		ModelAndView result;
		try {
			final Topic topic = this.topicService.findOne(idTopic);
			Assert.notNull(topic);
			this.topicService.delete(topic);
			result = new ModelAndView("redirect:list.do");

		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");

		}
		return result;

	}

}

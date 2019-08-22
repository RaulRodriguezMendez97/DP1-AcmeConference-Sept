
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import services.PanelService;
import domain.Conference;
import domain.Panel;

@Controller
@RequestMapping("/panel/administrator")
public class PanelAdministratorController extends AbstractController {

	@Autowired
	private PanelService		panelService;
	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Panel> panels = this.panelService.findAll();
		result = new ModelAndView("panel/list");
		result.addObject("panels", panels);
		return result;

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer panelId) {
		ModelAndView result;
		try {
			final Panel panel = this.panelService.findOne(panelId);
			Assert.notNull(panel);
			result = new ModelAndView("panel/show");
			result.addObject("panel", panel);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final Panel panel = this.panelService.create();
		result = new ModelAndView("panel/edit");
		result.addObject("panel", panel);
		result.addObject("conferences", this.conferenceService.getFutureAndDraftModeConferences());
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer panelId) {
		ModelAndView result;
		try {
			final Panel panel = this.panelService.findOne(panelId);
			Assert.notNull(panel);
			final Collection<Conference> conferences = this.conferenceService.getFutureAndDraftModeConferences();
			Assert.isTrue(conferences.contains(panel.getConference()));
			result = new ModelAndView("panel/edit");
			result.addObject("panel", panel);
			result.addObject("conferences", conferences);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(final Panel panel, final BindingResult binding) {
		ModelAndView result;
		try {
			final Panel p = this.panelService.reconstruct(panel, binding);
			final Collection<Conference> conferences = this.conferenceService.getFutureAndDraftModeConferences();
			if (!binding.hasErrors()) {
				Assert.isTrue(conferences.contains(panel.getConference()));
				this.panelService.save(p);
				result = new ModelAndView("redirect:list.do");
			} else {
				result = new ModelAndView("panel/edit");
				result.addObject("panel", panel);
				result.addObject("conferences", conferences);
			}
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Panel panel) {
		ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.getFutureAndDraftModeConferences();
		try {
			final Panel p = this.panelService.findOne(panel.getId());
			Assert.notNull(p);
			Assert.isTrue(conferences.contains(p.getConference()));
			this.panelService.delete(p);
			result = new ModelAndView("redirect:list.do");

		} catch (final Exception e) {
			result = new ModelAndView("panel/edit");
			result.addObject("panel", panel);
			result.addObject("conferences", conferences);
		}
		return result;

	}

}

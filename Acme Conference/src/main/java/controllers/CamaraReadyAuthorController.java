
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

import repositories.SubmissionRepository;
import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.AuthorService;
import services.CamaraReadyService;
import services.SubmissionService;
import domain.Actor;
import domain.Author;
import domain.CamaraReady;
import domain.Submission;

@Controller
@RequestMapping("/camera-ready/author")
public class CamaraReadyAuthorController extends AbstractController {

	@Autowired
	private CamaraReadyService		camaraReadyService;

	@Autowired
	private SubmissionService		submissionService;

	@Autowired
	private SubmissionRepository	submissionRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AuthorService			authorService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer idSubmission) {
		ModelAndView result;
		try {
			final String lang = LocaleContextHolder.getLocale().getLanguage();
			final CamaraReady camaraReady = this.camaraReadyService.getCameraReadyBySubmission(idSubmission);
			final Submission submission = this.submissionService.findOne(idSubmission);
			Assert.isTrue(submission.getStatus() == 2);

			if (camaraReady != null) {
				final UserAccount user = LoginService.getPrincipal();
				final Actor a = this.actorService.getActorByUserAccount(user.getId());
				Assert.isTrue(camaraReady.getAuthor().equals(a) || camaraReady.getCoAuthors().contains(a));
			}

			result = new ModelAndView("camera-ready/show");
			result.addObject("camaraReady", camaraReady);
			result.addObject("submission", submission);
			result.addObject("lang", lang);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");
		}
		return result;

	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer idSubmission) {
		ModelAndView result;
		try {

			final CamaraReady camaraReady = this.camaraReadyService.create();
			Assert.isTrue(idSubmission != null);
			Assert.isTrue(this.submissionService.findOne(idSubmission).getStatus() == 2);

			final Submission s = this.submissionRepository.findOne(idSubmission);

			final Collection<Author> coAuthors = this.authorService.getAuthorsUnlessConnected(s.getAuthor());

			result = new ModelAndView("camera-ready/edit");
			result.addObject("camaraReady", camaraReady);
			result.addObject("idSubmission", idSubmission);
			result.addObject("coAuthors", coAuthors);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");
		}
		return result;

	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer idCameraReady, @RequestParam final Integer idSubmission) {
		ModelAndView result;
		try {
			final CamaraReady camaraReady = this.camaraReadyService.findOne(idCameraReady);
			Assert.isTrue(idSubmission != null);
			Assert.isTrue(idCameraReady != null);

			final Submission s = this.submissionRepository.findOne(idSubmission);

			final UserAccount user = LoginService.getPrincipal();
			final Author a = (Author) this.actorService.getActorByUserAccount(user.getId());
			final Collection<Author> coAuthors = this.authorService.getAuthorsUnlessConnected(s.getAuthor());

			Assert.isTrue(camaraReady.getAuthor().equals(a) || camaraReady.getCoAuthors().contains(a));
			Assert.isTrue(this.submissionService.findOne(idSubmission).getStatus() == 2);

			result = new ModelAndView("camera-ready/edit");
			result.addObject("camaraReady", camaraReady);
			result.addObject("idSubmission", idSubmission);
			result.addObject("coAuthors", coAuthors);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../");
		}
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@RequestParam final Integer idSubmission, final CamaraReady camaraReady, final BindingResult binding) {

		ModelAndView result;

		try {
			final CamaraReady c = this.camaraReadyService.reconstruct(camaraReady, idSubmission, binding);
			if (!binding.hasErrors()) {
				final CamaraReady saved = this.camaraReadyService.save(c);
				final Submission submission = this.submissionService.findOne(idSubmission);
				submission.setCamaraReady(saved);
				this.submissionRepository.save(submission);
				result = new ModelAndView("redirect:show.do?idSubmission=" + idSubmission);
			} else {
				final Submission submission = this.submissionService.findOne(idSubmission);

				final Collection<Author> coAuthors = this.authorService.getAuthorsUnlessConnected(submission.getAuthor());
				result = new ModelAndView("camera-ready/edit");
				result.addObject("camaraReady", camaraReady);
				result.addObject("idSubmission", idSubmission);
				result.addObject("coAuthors", coAuthors);

			}
		} catch (final IllegalArgumentException e) {
			final Submission submission = this.submissionService.findOne(idSubmission);

			final Collection<Author> coAuthors = this.authorService.getAuthorsUnlessConnected(submission.getAuthor());
			result = new ModelAndView("camera-ready/edit");
			result.addObject("camaraReady", camaraReady);
			result.addObject("idSubmission", idSubmission);
			result.addObject("exception", e);
			result.addObject("coAuthors", coAuthors);

		}

		return result;
	}

}


package controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.AdministratorService;
import services.AuthorService;
import services.ConferenceService;
import services.ReportService;
import services.ReviwedService;
import services.ReviwerService;
import services.SubmissionService;
import domain.Administrator;
import domain.Author;
import domain.Conference;
import domain.Reviwed;
import domain.Reviwer;
import domain.Submission;
import forms.SubmissionReviwedForm;

@Controller
@RequestMapping("/submission")
public class SubmissionAuthorAdministratorReviwerController extends AbstractController {

	@Autowired
	private SubmissionService		submissionService;
	@Autowired
	private ConferenceService		conferenceService;
	@Autowired
	private ReviwedService			reviwedService;
	@Autowired
	private AuthorService			authorService;
	@Autowired
	private ReviwerService			reviwerService;
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private ReportService			reportService;


	@RequestMapping(value = "/author/list", method = RequestMethod.GET)
	public ModelAndView listAuthor() {
		final ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final Author a = this.authorService.getAuthorByUserAccount(user.getId());
		final Collection<Submission> submissions = this.submissionService.getSubmissionByAuthor(a.getId());

		result = new ModelAndView("submission/list");
		result.addObject("submissions", submissions);
		return result;
	}

	@RequestMapping(value = "/author/detail", method = RequestMethod.GET)
	public ModelAndView detailSubmissionAuthor(@RequestParam final Integer submissionId) {
		ModelAndView result;
		try {
			final Submission submission = this.submissionService.findOneAuthor(submissionId);
			final Conference conference = this.conferenceService.findOne(submission.getConference().getId());
			final Reviwed reviwed = this.reviwedService.findOne(submission.getReviwed().getId());
			Assert.notNull(submission);
			Assert.notNull(conference);
			Assert.notNull(reviwed);

			result = new ModelAndView("submission/detail");
			result.addObject("submission", submission);
			result.addObject("conference", conference);
			result.addObject("reviwed", reviwed);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../");
		}
		return result;
	}

	@RequestMapping(value = "/author/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final Author a = this.authorService.getAuthorByUserAccount(user.getId());
		SubmissionReviwedForm submissionReviwedForm = new SubmissionReviwedForm();
		final Collection<Conference> conferences;
		final Collection<Author> coAuthors;

		coAuthors = this.authorService.findAll();
		coAuthors.remove(a);
		conferences = this.conferenceService.getConferencesSubmissionDeadlinePosteriorNow();
		submissionReviwedForm = submissionReviwedForm.create();
		Assert.notNull(submissionReviwedForm);

		result = new ModelAndView("submission/edit");
		result.addObject("submissionReviwedForm", submissionReviwedForm);
		result.addObject("conferences", conferences);
		result.addObject("coAuthors", coAuthors);
		return result;
	}

	@RequestMapping(value = "/author/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveSubmissionAuthor(@ModelAttribute("submissionReviwedForm") final SubmissionReviwedForm submissionReviwedForm, final BindingResult binding) {
		ModelAndView result;
		Submission submission = null;
		Reviwed reviwed = null;
		Reviwed reviwedSave = null;

		try {
			reviwed = this.reviwedService.reconstruct(submissionReviwedForm, binding);
			submissionReviwedForm.setReviwed(reviwed);
			submission = this.submissionService.reconstruct(submissionReviwedForm, binding);
			if (!binding.hasErrors()) {
				reviwedSave = this.reviwedService.save(reviwed);
				submission.setReviwed(reviwedSave);
				this.submissionService.saveAuthor(submission);
				result = new ModelAndView("redirect:list.do");
			} else {
				final UserAccount user = LoginService.getPrincipal();
				final Author a = this.authorService.getAuthorByUserAccount(user.getId());
				final Collection<Conference> conferences;
				final Collection<Author> coAuthors;
				coAuthors = this.authorService.findAll();
				coAuthors.remove(a);
				conferences = this.conferenceService.getConferencesSubmissionDeadlinePosteriorNow();
				result = new ModelAndView("submission/edit");
				result.addObject("submissionReviwedForm", submissionReviwedForm);
				result.addObject("conferences", conferences);
				result.addObject("coAuthors", coAuthors);
			}
		} catch (final Exception e) {
			final UserAccount user = LoginService.getPrincipal();
			final Author a = this.authorService.getAuthorByUserAccount(user.getId());
			final Collection<Conference> conferences;
			final Collection<Author> coAuthors;
			coAuthors = this.authorService.findAll();
			coAuthors.remove(a);
			conferences = this.conferenceService.getConferencesSubmissionDeadlinePosteriorNow();
			result = new ModelAndView("submission/edit");
			result.addObject("exception", e);
			result.addObject("submissionReviwedForm", submissionReviwedForm);
			result.addObject("conferences", conferences);
			result.addObject("coAuthors", coAuthors);
		}
		return result;
	}

	@RequestMapping(value = "/reviwer/list", method = RequestMethod.GET)
	public ModelAndView listReviwer() {
		final ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final Reviwer r = this.reviwerService.getReviwerByUserAccount(user.getId());
		final Collection<Submission> submissions = this.submissionService.getSubmissionByReviwer(r.getId());

		result = new ModelAndView("submission/list");
		result.addObject("submissions", submissions);
		return result;
	}

	//ADMINISTRATOR

	@RequestMapping(value = "/administrator/submissionsUnderReviwed", method = RequestMethod.GET)
	public ModelAndView listAdministratorUnderReviwed() {
		final ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final Administrator admin = this.administratorService.getAdministratorByUserAccount(user.getId());
		final Collection<Submission> submissions = this.submissionService.getSubmissionByAdministratorStatus0(admin.getId());

		final Date fechaActual = new Date();

		result = new ModelAndView("submission/list");
		result.addObject("submissions", submissions);
		result.addObject("fechaActual", fechaActual);
		result.addObject("uriL", "submission/administrator/submissionsUnderReviwed.do");
		result.addObject("uriD", "submission/administrator/detailSubmissionUnderReviwed.do");
		result.addObject("uriDR", "submission/administrator/detailReportsSubmissionUnderReviwed.do");
		return result;
	}

	@RequestMapping(value = "/administrator/detailSubmissionUnderReviwed", method = RequestMethod.GET)
	public ModelAndView detailSubmissionAdministratorUnderReviwed(@RequestParam final Integer submissionId) {
		ModelAndView result;
		try {
			final Submission submission = this.submissionService.findOneAdministrator(submissionId);
			final Conference conference = this.conferenceService.findOne(submission.getConference().getId());
			final Reviwed reviwed = this.reviwedService.findOne(submission.getReviwed().getId());
			Assert.notNull(submission);
			Assert.notNull(conference);
			Assert.notNull(reviwed);

			result = new ModelAndView("submission/detail");
			result.addObject("submission", submission);
			result.addObject("conference", conference);
			result.addObject("reviwed", reviwed);
			result.addObject("reviwed", reviwed);
			result.addObject("uri", "submission/administrator/submissionsUnderReviwed.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../");
		}
		return result;
	}

	@RequestMapping(value = "/administrator/submissionsRejected", method = RequestMethod.GET)
	public ModelAndView listAdministratorRejected() {
		final ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final Administrator admin = this.administratorService.getAdministratorByUserAccount(user.getId());
		final Collection<Submission> submissions = this.submissionService.getSubmissionByAdministratorStatus1(admin.getId());

		result = new ModelAndView("submission/list");
		result.addObject("submissions", submissions);
		result.addObject("uriL", "submission/administrator/submissionsRejected.do");
		result.addObject("uriD", "submission/administrator/detailSubmissionRejected.do");
		result.addObject("uriDR", "submission/administrator/detailReportsSubmissionRejected.do");
		return result;
	}

	@RequestMapping(value = "/administrator/detailSubmissionRejected", method = RequestMethod.GET)
	public ModelAndView detailSubmissionAdministratorRejected(@RequestParam final Integer submissionId) {
		ModelAndView result;
		try {
			final Submission submission = this.submissionService.findOneAdministrator(submissionId);
			final Conference conference = this.conferenceService.findOne(submission.getConference().getId());
			final Reviwed reviwed = this.reviwedService.findOne(submission.getReviwed().getId());
			Assert.notNull(submission);
			Assert.notNull(conference);
			Assert.notNull(reviwed);

			result = new ModelAndView("submission/detail");
			result.addObject("submission", submission);
			result.addObject("conference", conference);
			result.addObject("reviwed", reviwed);
			result.addObject("reviwed", reviwed);
			result.addObject("uri", "submission/administrator/submissionsRejected.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../");
		}
		return result;
	}

	@RequestMapping(value = "/administrator/submissionsAccepted", method = RequestMethod.GET)
	public ModelAndView listAdministratorAccepted() {
		final ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		final Administrator admin = this.administratorService.getAdministratorByUserAccount(user.getId());
		final Collection<Submission> submissions = this.submissionService.getSubmissionByAdministratorStatus2(admin.getId());

		result = new ModelAndView("submission/list");
		result.addObject("submissions", submissions);
		result.addObject("uriL", "submission/administrator/submissionsAccepted.do");
		result.addObject("uriD", "submission/administrator/detailSubmissionAccepted.do");
		result.addObject("uriDR", "submission/administrator/detailReportsSubmissionAccepted.do");
		return result;
	}

	@RequestMapping(value = "/administrator/detailSubmissionAccepted", method = RequestMethod.GET)
	public ModelAndView detailSubmissionAdministrator(@RequestParam final Integer submissionId) {
		ModelAndView result;
		try {
			final Submission submission = this.submissionService.findOneAdministrator(submissionId);
			final Conference conference = this.conferenceService.findOne(submission.getConference().getId());
			final Reviwed reviwed = this.reviwedService.findOne(submission.getReviwed().getId());
			Assert.notNull(submission);
			Assert.notNull(conference);
			Assert.notNull(reviwed);

			result = new ModelAndView("submission/detail");
			result.addObject("submission", submission);
			result.addObject("conference", conference);
			result.addObject("reviwed", reviwed);
			result.addObject("reviwed", reviwed);
			result.addObject("uri", "submission/administrator/submissionsAccepted.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../");
		}
		return result;
	}

	@RequestMapping(value = "/administrator/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int submissionId) {
		ModelAndView result;
		try {
			final Submission submission = this.submissionService.findOneAdministrator(submissionId);
			final Collection<Reviwer> reviwers = this.reviwerService.findAll();
			Assert.notNull(submission);
			result = new ModelAndView("submission/edit");
			result.addObject("submission", submission);
			result.addObject("reviwers", reviwers);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:submissionsUnderReviwed.do");
		}
		return result;
	}

	@RequestMapping(value = "/administrator/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveSubmissionAdministrator(final Submission submission, final BindingResult binding) {
		ModelAndView result;

		try {
			final Submission s = this.submissionService.reconstructSubmissionAdministrator(submission, binding);
			if (!binding.hasErrors()) {
				this.submissionService.saveAdmin(s);
				result = new ModelAndView("redirect:submissionsUnderReviwed.do");
			} else {
				final Collection<Reviwer> reviwers = this.reviwerService.findAll();
				result = new ModelAndView("submission/edit");
				result.addObject("submission", submission);
				result.addObject("reviwers", reviwers);
			}
		} catch (final Exception e) {
			final Collection<Reviwer> reviwers = this.reviwerService.findAll();
			result = new ModelAndView("submission/edit");
			result.addObject("exception", e);
			result.addObject("submission", submission);
			result.addObject("reviwers", reviwers);
		}
		return result;
	}

	@RequestMapping(value = "/administrator/detailReportsSubmissionAccepted", method = RequestMethod.GET)
	public ModelAndView detailsReportsSubAccepted(@RequestParam final Integer submissionId) {
		ModelAndView result;
		try {
			final int reportAccepted = this.reportService.getReportsDecicionAceptadaBySubmission(submissionId);
			final int reportRejected = this.reportService.getReportsDecicionRechazadaBySubmission(submissionId);
			final int reportBorderLine = this.reportService.getReportsDecicionBorderLineBySubmission(submissionId);

			result = new ModelAndView("submission/detailReports");
			result.addObject("reportAccepted", reportAccepted);
			result.addObject("reportRejected", reportRejected);
			result.addObject("reportBorderLine", reportBorderLine);
			result.addObject("uri", "submission/administrator/submissionsAccepted.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../");
		}
		return result;
	}

	@RequestMapping(value = "/administrator/detailReportsSubmissionRejected", method = RequestMethod.GET)
	public ModelAndView detailsReportsSubRejected(@RequestParam final Integer submissionId) {
		ModelAndView result;
		try {
			final int reportAccepted = this.reportService.getReportsDecicionAceptadaBySubmission(submissionId);
			final int reportRejected = this.reportService.getReportsDecicionRechazadaBySubmission(submissionId);
			final int reportBorderLine = this.reportService.getReportsDecicionBorderLineBySubmission(submissionId);

			result = new ModelAndView("submission/detailReports");
			result.addObject("reportAccepted", reportAccepted);
			result.addObject("reportRejected", reportRejected);
			result.addObject("reportBorderLine", reportBorderLine);
			result.addObject("uri", "submission/administrator/submissionsRejected.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../");
		}
		return result;
	}

	@RequestMapping(value = "/administrator/detailReportsSubmissionUnderReviwed", method = RequestMethod.GET)
	public ModelAndView detailsReportsSubUnderReviwed(@RequestParam final Integer submissionId) {
		ModelAndView result;
		try {
			final int reportAccepted = this.reportService.getReportsDecicionAceptadaBySubmission(submissionId);
			final int reportRejected = this.reportService.getReportsDecicionRechazadaBySubmission(submissionId);
			final int reportBorderLine = this.reportService.getReportsDecicionBorderLineBySubmission(submissionId);

			result = new ModelAndView("submission/detailReports");
			result.addObject("reportAccepted", reportAccepted);
			result.addObject("reportRejected", reportRejected);
			result.addObject("reportBorderLine", reportBorderLine);
			result.addObject("uri", "submission/administrator/submissionsUnderReviwed.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../");
		}
		return result;
	}

}

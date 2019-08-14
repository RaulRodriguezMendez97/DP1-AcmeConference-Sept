
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ReviwedRepository;
import security.LoginService;
import security.UserAccount;
import domain.Reviwed;
import forms.SubmissionReviwedForm;

@Service
@Transactional
public class ReviwedService {

	@Autowired
	private ReviwedRepository	reviwedRepository;
	@Autowired
	private Validator			validator;


	//	public Reviwed create() {
	//		final Reviwed reviwed = new Reviwed();
	//		reviwed.setSummary("");
	//		reviwed.setTitle("");
	//		reviwed.setUrlDocument("");
	//		return reviwed;
	//	}

	public Reviwed findOne(final int reviwedId) {
		return this.reviwedRepository.findOne(reviwedId);
	}

	public Reviwed save(final Reviwed reviwed) {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
		final Reviwed reviwedSave = this.reviwedRepository.save(reviwed);
		return reviwedSave;
	}

	public Reviwed reconstruct(final SubmissionReviwedForm submissionReviwedForm, final BindingResult binding) {
		final Reviwed res = new Reviwed();
		if (submissionReviwedForm.getId() == 0) {
			res.setId(submissionReviwedForm.getId());
			res.setVersion(submissionReviwedForm.getVersion());
			res.setSummary(submissionReviwedForm.getSummary());
			res.setTitle(submissionReviwedForm.getTitle());
			res.setUrlDocument(submissionReviwedForm.getUrlDocument());
			this.validator.validate(res, binding);
		} /*
		 * else {
		 * Submission submission;
		 * submission = this.submissionService.findOne(submissionReviwedForm.getId());
		 * res = submission.getReviwed();
		 * final Reviwed p = new Reviwed();
		 * 
		 * p.setId(res.getId());
		 * p.setVersion(res.getVersion());
		 * p.setSummary(submissionReviwedForm.getSummary());
		 * p.setTitle(submissionReviwedForm.getTitle());
		 * p.setUrlDocument(submissionReviwedForm.getUrlDocument());
		 * this.validator.validate(p, binding);
		 * 
		 * if (binding.hasErrors())
		 * throw new ValidationException();
		 */
		//res = p;
		//}
		return res;
	}
}

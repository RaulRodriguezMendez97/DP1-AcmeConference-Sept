
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

	public Reviwed reconstruct(final Reviwed reviwed, final BindingResult binding) {
		Reviwed res = null;
		if (reviwed.getId() == 0) {
			/*
			 * final UserAccount user = LoginService.getPrincipal();
			 * final Author a = this.authorService.getAuthorByUserAccount(user.getId());
			 * res.setId(submissionReviwedForm.getId());
			 * res.setVersion(submissionReviwedForm.getVersion());
			 * res.setSummary(submissionReviwedForm.getSummary());
			 * res.setTitle(submissionReviwedForm.getTitle());
			 * res.setUrlDocument(submissionReviwedForm.getUrlDocument());
			 * res.setCoAuthors(submissionReviwedForm.getCoAuthors());
			 * res.setAuthor(a);
			 */
			res = reviwed;
			this.validator.validate(res, binding);
		}
		return res;
	}
}

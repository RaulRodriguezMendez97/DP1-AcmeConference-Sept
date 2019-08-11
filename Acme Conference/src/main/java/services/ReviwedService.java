
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReviwedRepository;
import security.LoginService;
import security.UserAccount;
import domain.Reviwed;

@Service
@Transactional
public class ReviwedService {

	@Autowired
	private ReviwedRepository	reviwedRepository;


	public Reviwed create() {
		final Reviwed reviwed = new Reviwed();
		reviwed.setSummary("");
		reviwed.setTitle("");
		reviwed.setUrlDocument("");
		return reviwed;
	}

	public Reviwed findOne(final int reviwedId) {
		return this.reviwedRepository.findOne(reviwedId);
	}

	public Reviwed save(final Reviwed reviwed) {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
		final Reviwed reviwedSave = this.reviwedRepository.save(reviwed);
		return reviwedSave;
	}
}

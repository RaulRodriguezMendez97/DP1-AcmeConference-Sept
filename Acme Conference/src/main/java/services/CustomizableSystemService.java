
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomizableSystemRepository;
import security.UserAccount;
import domain.CustomizableSystem;

@Service
@Transactional
public class CustomizableSystemService {

	@Autowired
	private CustomizableSystemRepository	customizableSystemRepository;

	@Autowired
	private ActorService					actorService;


	public CustomizableSystem create() {
		final CustomizableSystem res = new CustomizableSystem();
		res.setNameSystem("");
		res.setBanner("");
		res.setMessageWelcomePage("");
		res.setSpanishMessageWelcomePage("");
		res.setTelephoneCode("");
		res.setBrandNameCredictCard(new ArrayList<String>());

		return res;
	}

	public Collection<CustomizableSystem> findAll() {
		return this.customizableSystemRepository.findAll();
	}

	public CustomizableSystem findOne(final int customizableSystemId) {
		return this.customizableSystemRepository.findOne(customizableSystemId);
	}

	//updating
	public CustomizableSystem save(final CustomizableSystem customizableSystem) {
		final UserAccount user = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(user.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		return this.customizableSystemRepository.save(customizableSystem);
	}

	public String getWelcomeMessage() {
		return this.customizableSystemRepository.getWelcomeMessage();
	}

	public String getSpanishWelcomeMessage() {
		return this.customizableSystemRepository.getSpanishWelcomeMessage();
	}

	public String getTelephoneCode() {
		return this.customizableSystemRepository.getTelephoneCode();
	}

	public String getUrlBanner() {
		return this.customizableSystemRepository.getUrlBanner();
	}

	public String getNameApp() {
		return this.customizableSystemRepository.getNameApp();
	}

}

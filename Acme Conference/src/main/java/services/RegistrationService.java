
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.RegistrationRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Conference;
import domain.CreditCard;
import domain.Registration;

@Service
@Transactional
public class RegistrationService {

	@Autowired
	private RegistrationRepository	registrationRepository;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private Validator				validator;


	public Registration create() {
		final Registration registration = new Registration();
		registration.setConference(new Conference());
		registration.setCreditCard(new CreditCard());

		return registration;
	}

	public Collection<Registration> findAll() {
		return this.registrationRepository.findAll();
	}
	public Registration findOne(final int registrationId) {
		final Registration registration = this.registrationRepository.findOne(registrationId);
		final UserAccount userLoged = LoginService.getPrincipal();
		final Actor a = this.actorService.getActorByUserAccount(userLoged.getId());
		Assert.isTrue(userLoged.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
		Assert.isTrue(registration.getCreditCard().getAuthor().equals(a));
		return this.registrationRepository.findOne(registrationId);
	}

	public Registration save(final Registration r) {

		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(user.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));

		final Conference conference = r.getConference();
		Assert.isTrue(conference.getStartDate().after(new Date()));

		Registration registration;
		registration = this.registrationRepository.save(r);

		return registration;
	}

}

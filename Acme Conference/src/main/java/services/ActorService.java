
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository	actorRepository;


	public Actor getActorByUserAccount(final Integer id) {
		return this.actorRepository.getActorByUserAccount(id);
	}

	public Actor getActorByEmail(final String email) {
		return this.actorRepository.getActorByEmail(email);
	}

	public Actor getActorLogged() {
		UserAccount userAccount;
		Actor actor;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		actor = this.actorRepository.getActorByUserAccount(userAccount.getId());
		Assert.notNull(actor);

		return actor;
	}

	public List<Actor> findAll() {
		return this.actorRepository.findAll();
	}

	public Actor findOne(final int id) {
		final UserAccount user = LoginService.getPrincipal();
		final Actor a = this.getActorByUserAccount(user.getId());
		final Actor res = this.actorRepository.findOne(id);
		Assert.isTrue(a.getId() == res.getId());
		return res;
	}

	public List<String> getEmails() {
		return this.actorRepository.getEmails();
	}

}


package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TopicRepository;
import security.LoginService;
import security.UserAccount;
import domain.Topic;

@Service
@Transactional
public class TopicService {

	@Autowired
	private TopicRepository	topicRepository;


	public Topic create() {
		final Topic res = new Topic();

		res.setName("");
		res.setSpanishName("");

		return res;
	}

	public Collection<Topic> findAll() {
		return this.topicRepository.findAll();
	}

	public Topic findOne(final Integer id) {
		return this.topicRepository.findOne(id);

	}

	public Topic save(final Topic t) {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));

		Topic saved;

		saved = this.topicRepository.save(t);

		return saved;
	}

	public void delete(final Topic t) {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));

		this.topicRepository.delete(t);
	}

}

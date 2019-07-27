
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.TopicRepository;
import domain.Topic;

@Service
@Transactional
public class TopicService {

	@Autowired
	private TopicRepository	topicRepository;


	public Collection<Topic> findAll() {
		return this.topicRepository.findAll();
	}

}

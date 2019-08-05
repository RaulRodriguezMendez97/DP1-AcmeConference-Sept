
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select m from Message m where ((locate(?1,m.sender.email) != 0 or locate(?1,m.receiver.email) != 0) and (locate(?2,m.topic.name) != 0)) ")
	Collection<Message> getMessagesByFinder(String email, String topic);

}

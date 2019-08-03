
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.MessageBox;

public interface MessageBoxRepository extends JpaRepository<MessageBox, Integer> {

	@Query("select mb from MessageBox mb where mb.actor.id = ?1")
	public MessageBox getMessageBoxByActor(Integer id);

}

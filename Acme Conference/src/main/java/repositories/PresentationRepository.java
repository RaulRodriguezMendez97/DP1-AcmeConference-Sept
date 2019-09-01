
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Presentation;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Integer> {

	@Query("select p from Presentation p where p.conference.admin.id = ?1 ")
	public Collection<Presentation> findAllByAdmin(int adminId);

}

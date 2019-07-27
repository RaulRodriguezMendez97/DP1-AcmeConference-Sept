
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

	/*
	 * @Query(value = "select c from Conference c where c.startDate < CURRENT_DATE", nativeQuery = true)
	 * public Collection<Conference> getAllConferenceForthComing();
	 */
}

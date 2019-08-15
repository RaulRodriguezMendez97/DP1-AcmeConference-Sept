
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CamaraReady;

@Repository
public interface CamaraReadyRepository extends JpaRepository<CamaraReady, Integer> {

	@Query("select s.camaraReady from Submission s where s.id = ?1")
	public CamaraReady getCameraReadyBySubmission(Integer id);

}

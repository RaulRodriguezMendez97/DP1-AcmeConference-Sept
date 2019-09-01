
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CamaraReady;

@Repository
public interface CamaraReadyRepository extends JpaRepository<CamaraReady, Integer> {

	@Query("select s.camaraReady from Submission s where s.id = ?1")
	public CamaraReady getCameraReadyBySubmission(Integer id);

	@Query("select s.camaraReady from Submission s where s.conference.id = ?1 and s.status = 2")
	public Collection<CamaraReady> getCameraReadyByConference(final Integer id);

	@Query("select p.camaraReady from Presentation p where p.conference.admin.id = ?1")
	public Collection<CamaraReady> getUsedCamerasByAdmin(int adminId);

}

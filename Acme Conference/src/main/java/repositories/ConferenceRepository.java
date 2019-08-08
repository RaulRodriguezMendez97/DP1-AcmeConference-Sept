
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

	@Query("select c from Conference c where c.admin.id = ?1")
	public Collection<Conference> getConferencesByAdmin(Integer idAdmin);

	@Query("select c from Conference c where c.finalMode = 1")
	public Collection<Conference> getConferencesInSaveMode();

	@Query("select c from Conference c where ((c.title like %?1% or c.venue like %?1% or c.summary like %?1%) and (c.finalMode = 1))")
	public Collection<Conference> getConferencesByFinder(String keyWord);

	@Query(value = "SELECT * FROM `acme-conference`.conference where CURDATE() BETWEEN start_date AND end_date AND finalMode = 1", nativeQuery = true)
	public Collection<Conference> getActivesConferences();

	@Query(value = "SELECT * FROM `acme-conference`.conference where CURDATE() < start_date AND finalMode = 1", nativeQuery = true)
	public Collection<Conference> getIncomingConferences();

	@Query(value = "SELECT * FROM `acme-conference`.conference where CURDATE() > end_date AND finalMode = 1", nativeQuery = true)
	public Collection<Conference> getPassConferences();

	//Dashboard

	@Query("select avg(1.0*(select count(s.conference) from Submission s where s.conference.id = c.id)), min(1.0*(select count(s.conference) from Submission s where s.conference.id = c.id)), max(1.0*(select count(s.conference) from Submission s where s.conference.id = c.id)), sqrt(1.0*sum(1.0*(select count (s.conference) from Submission s where s.conference.id = c.id) * (select count(s.conference) from Submission s where s.conference.id = c.id)) / count(c) - avg(1.0*(select count(s.conference) from Submission s where s.conference.id = c.id)) * avg(1.0*(select count(s.conference) from Submission s where s.conference.id = c.id))) from Conference c")
	public List<Object[]> getAvgMinMaxDesvSubmissionsByConference();

	@Query("select avg(1.0*(select count(r.conference) from Registration r where r.conference.id = c.id)), min(1.0*(select count(r.conference) from Registration r where r.conference.id = c.id)), max(1.0*(select count(r.conference) from Registration r where r.conference.id = c.id)), sqrt(1.0*sum(1.0*(select count (r.conference) from Registration r where r.conference.id = c.id) * (select count(r.conference) from Registration r where r.conference.id = c.id)) / count(c) - avg(1.0*(select count(r.conference) from Registration r where r.conference.id = c.id)) * avg(1.0*(select count(r.conference) from Registration r where r.conference.id = c.id))) from Conference c")
	public List<Object[]> getAvgMinMaxDesvRegistrationByConference();

	@Query("select avg(c.fee), min(c.fee), max(c.fee), sqrt(sum(c.fee * c.fee)/count(c)-avg(c.fee)*avg(c.fee)) from Conference c")
	public List<Object[]> getAvgMinMaxDesvFeesByConference();

	@Query(
		value = "SELECT AVG(DATEDIFF(end_date, start_date)), MIN(DATEDIFF(end_date, start_date)), MAX(DATEDIFF(end_date, start_date)), SQRT(SUM(DATEDIFF(end_date, start_date) * DATEDIFF(end_date, start_date))/ COUNT(*)- AVG(DATEDIFF(end_date, start_date))*AVG(DATEDIFF(end_date, start_date)))FROM `acme-conference`.`conference`",
		nativeQuery = true)
	public List<Object[]> getAvgMinMaxDesvDaysByConference();

}

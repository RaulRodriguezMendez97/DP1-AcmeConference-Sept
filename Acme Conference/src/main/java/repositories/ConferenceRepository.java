
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

	/*
	 * @Query(value = "select c from Conference c where c.startDate < CURRENT_DATE", nativeQuery = true)
	 * public Collection<Conference> getAllConferenceForthComing();
	 */

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

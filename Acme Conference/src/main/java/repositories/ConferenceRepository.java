
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

	@Query("select r.conference from Registration  r where r.creditCard.author.id=?1")
	public Collection<Conference> getAllConferenceByAuthor(final int authorId);

	@Query("select c from Conference c where c.admin.id = ?1")
	public Collection<Conference> getConferencesByAdmin(Integer idAdmin);

	@Query("select c from Conference c where c.finalMode = 1")
	public Collection<Conference> getConferencesInSaveMode();

	@Query("select c from Conference c where ((c.title like %?1% or c.venue like %?1% or c.summary like %?1%) and (c.finalMode = 1))")
	public Collection<Conference> getConferencesByFinder(String keyWord);

	@Query("select c from Conference c where (now() between c.startDate and c.endDate) and c.finalMode = 1")
	public Collection<Conference> getActivesConferences();

	@Query("select c from Conference c where c.startDate > now() and c.finalMode = 1")
	public Collection<Conference> getIncomingConferences();

	@Query("select c from Conference c where now() > c.endDate and c.finalMode = 1")
	public Collection<Conference> getPastConferences();

	@Query("select c from Conference c where (datediff(curdate(), c.submissionDeadline)<= 5 and datediff(curdate(), c.submissionDeadline)>0) and c.finalMode=1")
	public Collection<Conference> getConferencesSubmissionLast5Days();

	@Query("select c from Conference c where (datediff(c.notificacionDeadline, curdate()) < 5 and datediff(c.notificacionDeadline, curdate()) >= 0) and c.finalMode=1")
	public Collection<Conference> getConferencesNotificationLess5Days();

	@Query("select c from Conference c where (datediff(c.cameraDeadline, curdate()) < 5 and datediff(c.cameraDeadline, curdate()) >= 0) and c.finalMode=1")
	public Collection<Conference> getConferencesCameraLess5Days();

	@Query("select c from Conference c where (datediff(c.startDate, curdate()) < 5 and datediff(c.startDate, curdate()) >= 0) and c.finalMode=1")
	public Collection<Conference> getConferencesStartLess5Days();

	//Raul
	@Query("select c from Conference c where curdate() <= c.submissionDeadline and c.finalMode=1")
	public Collection<Conference> getConferencesSubmissionDeadLinePosteriorNow();

	//Dashboard
	@Query("select avg(1.0*(select count(s.conference) from Submission s where s.conference.id = c.id)), min(1.0*(select count(s.conference) from Submission s where s.conference.id = c.id)), max(1.0*(select count(s.conference) from Submission s where s.conference.id = c.id)), sqrt(1.0*sum(1.0*(select count (s.conference) from Submission s where s.conference.id = c.id) * (select count(s.conference) from Submission s where s.conference.id = c.id)) / count(c) - avg(1.0*(select count(s.conference) from Submission s where s.conference.id = c.id)) * avg(1.0*(select count(s.conference) from Submission s where s.conference.id = c.id))) from Conference c")
	public List<Object[]> getAvgMinMaxDesvSubmissionsByConference();

	@Query("select avg(1.0*(select count(r.conference) from Registration r where r.conference.id = c.id)), min(1.0*(select count(r.conference) from Registration r where r.conference.id = c.id)), max(1.0*(select count(r.conference) from Registration r where r.conference.id = c.id)), sqrt(1.0*sum(1.0*(select count (r.conference) from Registration r where r.conference.id = c.id) * (select count(r.conference) from Registration r where r.conference.id = c.id)) / count(c) - avg(1.0*(select count(r.conference) from Registration r where r.conference.id = c.id)) * avg(1.0*(select count(r.conference) from Registration r where r.conference.id = c.id))) from Conference c")
	public List<Object[]> getAvgMinMaxDesvRegistrationByConference();

	@Query("select avg(c.fee), min(c.fee), max(c.fee), sqrt(sum(c.fee * c.fee)/count(c)-avg(c.fee)*avg(c.fee)) from Conference c")
	public List<Object[]> getAvgMinMaxDesvFeesByConference();

	@Query("select avg(datediff(c.endDate, c.startDate)), min(datediff(c.endDate, c.startDate)),max(datediff(c.endDate, c.startDate)), sqrt(sum(datediff(c.endDate, c.startDate)* datediff(c.endDate, c.startDate)) / count(c) - avg(datediff(c.endDate, c.startDate)) *avg(datediff(c.endDate, c.startDate))) from Conference c")
	public List<Object[]> getAvgMinMaxDesvDaysByConference();

	@Query("select c from Conference c where c.finalMode = 0")
	public Collection<Conference> getConferencesInDraftMode();

	@Query("select c from Conference c where curdate() < c.startDate and c.finalMode=1 and c.admin.id = ?1")
	public Collection<Conference> getFutureAndFinalModeConferences(int adminId);

}

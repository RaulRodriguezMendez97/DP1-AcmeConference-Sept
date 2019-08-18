
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	@Query("select r from Report r where r.submission.id=?1")
	public Collection<Report> getReportsBySubmission(Integer submissionId);

	@Query("select r from Report r where r.reviwer.id=?1")
	public Collection<Report> getReportsByReviwer(Integer reviwerId);

	@Query("select count(r) from Report r where r.submission.id =?1 and r.decision=2")
	public int getReportsConDecisionIgualAceptadaBySubmission(final int submissionId);

	@Query("select count(r) from Report r where r.submission.id =?1 and r.decision=0")
	public int getReportsConDecisionIgualRechazadaBySubmission(final int submissionId);

	@Query("select count(r) from Report r where r.submission.id =?1 and r.decision=1")
	public int getReportsConDecisionIgualBorderLineBySubmission(final int submissionId);
}

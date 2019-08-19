
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Reviwer;

@Repository
public interface ReviwerRepository extends JpaRepository<Reviwer, Integer> {

	@Query("select r from Reviwer r where r.userAccount.id = ?1")
	public Reviwer getReviwerByUserAccount(int userAccountId);

	@Query("select r from Reviwer r where r.submission.id=?1")
	public Collection<Reviwer> getReviwersBySubmission(final int submissionId);

}


package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

}

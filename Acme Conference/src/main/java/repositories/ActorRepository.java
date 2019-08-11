
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Conference;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id = ?1")
	public Actor getActorByUserAccount(Integer id);

	@Query(" select a from Actor a where a.email = ?1 ")
	public Actor getActorByEmail(String email);

	@Query("select a.email from Actor a")
	public List<String> getEmails();

	@Query("select s.author from Submission s where s.status = 2 and s.conference=?1")
	public Collection<Actor> getAuthorWithSubmission(Conference conference);

}

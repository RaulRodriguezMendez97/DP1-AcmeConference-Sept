
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Author;
import domain.Conference;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	@Query("select a from Author a where a.userAccount.id = ?1")
	public Author getAuthorByUserAccount(int userAccountId);

	@Query("select s.author from Submission s where s.status = 2 and s.conference=?1")
	public Collection<Author> getAuthorWithSubmission(Conference conference);

}

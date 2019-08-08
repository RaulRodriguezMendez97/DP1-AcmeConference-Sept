
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Reviwed;

@Repository
public interface ReviwedRepository extends JpaRepository<Reviwed, Integer> {

}

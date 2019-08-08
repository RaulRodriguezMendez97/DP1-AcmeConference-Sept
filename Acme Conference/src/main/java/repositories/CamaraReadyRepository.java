
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.CamaraReady;

@Repository
public interface CamaraReadyRepository extends JpaRepository<CamaraReady, Integer> {

}

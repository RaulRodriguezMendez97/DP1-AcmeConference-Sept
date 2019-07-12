
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.CustomizableSystem;

@Repository
public interface CustomizableSystemRepository extends JpaRepository<CustomizableSystem, Integer> {

}

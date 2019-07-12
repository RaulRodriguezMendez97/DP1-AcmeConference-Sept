
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CreditCardType;

@Repository
public interface CreditCardTypeRepository extends JpaRepository<CreditCardType, Integer> {

	@Query("select c.brandName from CreditCardType c")
	public Collection<String> getAllBrandName();
}

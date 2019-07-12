
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardTypeRepository;
import domain.CreditCardType;

@Service
@Transactional
public class CreditCardTypeService {

	@Autowired
	private CreditCardTypeRepository	creditCardTypeRepository;


	public CreditCardType create() {
		final CreditCardType c = new CreditCardType();
		c.setBrandName("");
		return c;
	}

	public Collection<CreditCardType> findAll() {
		return this.creditCardTypeRepository.findAll();
	}
	public CreditCardType findOne(final Integer creditCardTypeId) {
		return this.creditCardTypeRepository.findOne(creditCardTypeId);
	}
	public CreditCardType save(final CreditCardType c) {
		final Collection<CreditCardType> types = this.creditCardTypeRepository.findAll();
		Assert.isTrue(!types.contains(c), " CreditCartdTypeService.save -> This type have already exist.");
		Assert.isTrue(c != null && c.getBrandName() != null && c.getBrandName() != "");
		return this.creditCardTypeRepository.save(c);
	}
	public void delete(final CreditCardType c) {
		Assert.isTrue(!(c.getBrandName().equals("VISA") || c.getBrandName().equals("MASTER") || c.getBrandName().equals("DINNERS") || c.getBrandName().equals("AMEX")), "CreditCardTypeService.delete -> You can't delete a default type.");
		this.creditCardTypeRepository.delete(c);
	}

	public Collection<String> getBrandName() {
		return this.creditCardTypeRepository.getAllBrandName();
	}
}

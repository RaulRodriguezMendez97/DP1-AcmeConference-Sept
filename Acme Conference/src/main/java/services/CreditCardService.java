
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import security.LoginService;
import security.UserAccount;
import domain.Author;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	@Autowired
	private CreditCardRepository	creditCardRepository;
	@Autowired
	private AuthorService			authorService;


	public CreditCard create() {
		final CreditCard cc = new CreditCard();
		cc.setBrandName("");
		cc.setHoldName("");
		cc.setNumber("");
		cc.setExpirationMonth(0);
		cc.setExpirationYear(0);
		cc.setCW(0);
		return cc;
	}

	public Collection<CreditCard> findAll() {
		return this.creditCardRepository.findAll();
	}

	public CreditCard findOne(final Integer creditCardId) {
		return this.creditCardRepository.findOne(creditCardId);
	}

	public CreditCard save(final CreditCard cc) {
		final Collection<String> creditCardsNumbers = this.getAllNumbers();

		if (cc.getId() != 0) {

			final CreditCard creditCard = this.findOne(cc.getId());
			final String number = creditCard.getNumber();
			creditCardsNumbers.remove(number);
		}
		Assert.isTrue(!creditCardsNumbers.contains(cc.getNumber()));

		Assert.isTrue(cc != null && cc.getBrandName() != null && cc.getHoldName() != null && cc.getBrandName() != "" && cc.getHoldName() != "");
		return this.creditCardRepository.save(cc);

	}

	public Collection<String> getAllNumbers() {
		return this.creditCardRepository.getAllNumberCreditCards();
	}

	public Collection<CreditCard> getCreditCardByAuthor() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(user.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
		final Author author = this.authorService.getAuthorByUserAccount(user.getId());
		return this.creditCardRepository.getAllMyCreditCards(author.getId());
	}

}

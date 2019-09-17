
package services;

import java.util.Calendar;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CreditCardRepository;
import security.LoginService;
import security.UserAccount;
import domain.Author;
import domain.CreditCard;
import forms.RegistrationAndCreditCardForm;

@Service
@Transactional
public class CreditCardService {

	@Autowired
	private CreditCardRepository	creditCardRepository;
	@Autowired
	private AuthorService			authorService;

	@Autowired
	private Validator				validator;


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

	public CreditCard reconstruct(final RegistrationAndCreditCardForm registrationForm, final BindingResult binding) {
		CreditCard res = new CreditCard();

		final UserAccount user = LoginService.getPrincipal();
		final Author author = this.authorService.getAuthorByUserAccount(user.getId());

		if (registrationForm.getCreditCard() == null) {

			res.setId(registrationForm.getId());
			res.setVersion(registrationForm.getVersion());
			res.setBrandName(registrationForm.getBrandName());
			res.setHoldName(registrationForm.getHoldName());
			res.setNumber(registrationForm.getNumber());
			res.setExpirationMonth(registrationForm.getExpirationMonth());
			res.setExpirationYear(registrationForm.getExpirationYear());
			res.setCW(registrationForm.getCW());

			res.setAuthor(author);

			final Collection<String> creditCardsNumbers = this.getAllNumbers();
			if (creditCardsNumbers.contains(res.getNumber()))
				binding.rejectValue("number", "NumeroRepetido");

			final Calendar cal = Calendar.getInstance();
			final int añoActual = cal.get(Calendar.YEAR);
			final int mesActual = cal.get(Calendar.MONTH) + 1;

			if (res.getExpirationYear() < añoActual || (res.getExpirationMonth() <= mesActual && res.getExpirationYear() == añoActual))
				binding.rejectValue("expirationYear", "FechaNoValida");

			this.validator.validate(res, binding);

		} else {

			res = registrationForm.getCreditCard();
			res.setAuthor(author);

			final Collection<String> creditCardsNumbers = this.getAllNumbers();
			final CreditCard creditCard = this.findOne(res.getId());
			final String number = creditCard.getNumber();
			creditCardsNumbers.remove(number);
			if (creditCardsNumbers.contains(res.getNumber()))
				binding.rejectValue("number", "NumeroRepetido");

			final Calendar cal = Calendar.getInstance();
			final int añoActual = cal.get(Calendar.YEAR);
			final int mesActual = cal.get(Calendar.MONTH) + 1;
			if (res.getExpirationYear() < añoActual || (res.getExpirationMonth() <= mesActual && res.getExpirationYear() == añoActual))
				binding.rejectValue("creditCard", "CreditCardCaducada");

			this.validator.validate(res, binding);

		}
		return res;
	}

}

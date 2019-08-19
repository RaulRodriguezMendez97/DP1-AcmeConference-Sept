/*
 * DomainEntity.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package forms;

import domain.Author;
import domain.Conference;
import domain.CreditCard;

public class RegistrationAndCreditCardForm extends CreditCard {

	// Constructors -----------------------------------------------------------

	public RegistrationAndCreditCardForm() {
		super();
	}


	// Properties -------------------------------------------------------------

	private CreditCard	creditCard;

	private Conference	conference;


	// Business methods -------------------------------------------------------

	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Conference getConference() {
		return this.conference;
	}

	public void setConference(final Conference conference) {
		this.conference = conference;
	}

	public RegistrationAndCreditCardForm createToRegistrationAndCreditCard() {

		final RegistrationAndCreditCardForm registrationForm = new RegistrationAndCreditCardForm();

		registrationForm.setBrandName("");
		registrationForm.setHoldName("");
		registrationForm.setNumber("");
		registrationForm.setExpirationMonth(0);
		registrationForm.setExpirationYear(0);
		registrationForm.setCW(0);
		registrationForm.setAuthor(new Author());
		registrationForm.setCreditCard(new CreditCard());
		registrationForm.setConference(new Conference());

		return registrationForm;
	}

}

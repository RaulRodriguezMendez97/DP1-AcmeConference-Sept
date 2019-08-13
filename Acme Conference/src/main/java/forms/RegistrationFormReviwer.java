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

import java.util.HashSet;

import javax.validation.constraints.Size;

import security.Authority;
import security.UserAccount;
import domain.Reviwer;

public class RegistrationFormReviwer extends Reviwer {

	// Constructors -----------------------------------------------------------

	public RegistrationFormReviwer() {
		super();
	}


	// Properties -------------------------------------------------------------

	private String	password;

	private Boolean	patternPhone;


	public Boolean getPatternPhone() {
		return this.patternPhone;
	}

	public void setPatternPhone(final Boolean patternPhone) {
		this.patternPhone = patternPhone;
	}

	@Size(min = 5, max = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	// Business methods -------------------------------------------------------

	public RegistrationFormReviwer createToReviwer() {

		final RegistrationFormReviwer registrationForm = new RegistrationFormReviwer();

		registrationForm.setPatternPhone(false);
		registrationForm.setAddress("");
		registrationForm.setEmail("");
		registrationForm.setName("");
		registrationForm.setPhoto("");
		registrationForm.setAddress("");
		registrationForm.setPassword("");
		registrationForm.setKeyWords(new HashSet<String>());
		registrationForm.setMiddleName("");
		registrationForm.setSurname("");

		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.ADMIN);
		user.getAuthorities().add(ad);

		//NUEVO
		user.setUsername("");
		user.setPassword("");

		registrationForm.setUserAccount(user);

		return registrationForm;
	}

}


package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Actor extends DomainEntity {

	private String		name;
	private String		middleName;
	private String		surname;
	private String		photo;
	private String		email;
	private String		phone;
	private String		address;
	private Integer		numberSocialProfiles;
	private Integer		isBanned;
	private UserAccount	userAccount;


	//Getters and Setters

	@NotBlank
	@NotNull
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@NotBlank
	@NotNull
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	//	if email has following patterns, the value is well. 
	//	alias <identifier@domain>  -->  ^[A-z0-9]+\s*[A-z0-9\s]*\s\<[A-z0-9]+\@[A-z0-9]+\.[A-z0-9.]+\>
	//	identificador@  -->  ^[A-z0-9]+\@
	//	alias <identifier@>  -->  ^[A-z0-9]+\s*[A-z0-9\s]*\s\<[A-z0-9]+\@\>
	//@Email
	@Column(unique = true)
	@NotNull
	@NotBlank
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Pattern(regexp = "^\\+[1-9][0-9]{0,2}\\ \\([1-9][0-9]{0,2}\\)\\ [0-9]{4,}$|^\\+[1-9][0-9]{0,2}\\ [0-9]{4,}$|^[0-9]{4,}$")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	//OPCIONAL
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	//OPCIONAL
	public Integer getNumberSocialProfiles() {
		return this.numberSocialProfiles;
	}

	public void setNumberSocialProfiles(final Integer numberSocialProfiles) {
		this.numberSocialProfiles = numberSocialProfiles;
	}
	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@NotNull
	@Range(max = 1, min = 0)
	public Integer getIsBanned() {
		return this.isBanned;
	}

	public void setIsBanned(final Integer isBanned) {
		this.isBanned = isBanned;
	}

}

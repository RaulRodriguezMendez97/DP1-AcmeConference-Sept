
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {

	private String	holdName;
	private String	brandName;
	private String	number;
	private int		expirationMonth;
	private int		expirationYear;
	private int		CW;
	private Author	author;


	@ManyToOne(optional = false)
	@NotNull
	@Valid
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(final Author author) {
		this.author = author;
	}
	@NotBlank
	@NotNull
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getHoldName() {
		return this.holdName;
	}
	public void setHoldName(final String holdName) {
		this.holdName = holdName;
	}

	@NotBlank
	@NotNull
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getBrandName() {
		return this.brandName;
	}
	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	@Column(unique = true)
	@CreditCardNumber
	public String getNumber() {
		return this.number;
	}
	public void setNumber(final String number) {
		this.number = number;
	}

	public int getExpirationMonth() {
		return this.expirationMonth;
	}
	public void setExpirationMonth(final int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public int getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final int expirationYear) {
		this.expirationYear = expirationYear;
	}

	@Range(min = 100, max = 999)
	public int getCW() {
		return this.CW;
	}

	public void setCW(final int cW) {
		this.CW = cW;
	}

}

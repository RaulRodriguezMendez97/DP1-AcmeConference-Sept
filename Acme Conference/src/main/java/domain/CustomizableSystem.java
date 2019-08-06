
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class CustomizableSystem extends DomainEntity {

	private String				nameSystem;
	private String				banner;
	private String				messageWelcomePage;
	private String				telephoneCode;
	private String				spanishMessageWelcomePage;
	private Collection<String>	brandNameCreditCard;


	@NotBlank
	@NotNull
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getNameSystem() {
		return this.nameSystem;
	}

	public void setNameSystem(final String nameSystem) {
		this.nameSystem = nameSystem;
	}

	@NotBlank
	@NotNull
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getSpanishMessageWelcomePage() {
		return this.spanishMessageWelcomePage;
	}

	public void setSpanishMessageWelcomePage(final String spanishMessageWelcomePage) {
		this.spanishMessageWelcomePage = spanishMessageWelcomePage;
	}

	/*
	 * @NotBlank
	 * 
	 * @NotNull
	 * public String getBrandNameCredictCard() {
	 * return this.brandNameCredictCard;
	 * }
	 * 
	 * public void setBrandNameCredictCard(final String brandNameCredictCard) {
	 * this.brandNameCredictCard = brandNameCredictCard;
	 * }
	 */

	@NotBlank
	@NotNull
	@URL
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@NotBlank
	@NotNull
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getMessageWelcomePage() {
		return this.messageWelcomePage;
	}

	public void setMessageWelcomePage(final String messageWelcomePage) {
		this.messageWelcomePage = messageWelcomePage;
	}

	@NotBlank
	@NotNull
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getTelephoneCode() {
		return this.telephoneCode;
	}

	public void setTelephoneCode(final String telephoneCode) {
		this.telephoneCode = telephoneCode;
	}

	@ElementCollection
	public Collection<String> getBrandNameCreditCard() {
		return this.brandNameCreditCard;
	}

	public void setBrandNameCreditCard(final Collection<String> brandNameCreditCard) {
		this.brandNameCreditCard = brandNameCreditCard;
	}

}

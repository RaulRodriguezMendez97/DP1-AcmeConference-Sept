
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class CustomizableSystem extends DomainEntity {

	private String	name;
	private String	banner;
	private String	messageWelcomePage;
	private String	VATPercentage;
	private String	telephoneCode;


	@NotBlank
	@NotNull
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@NotNull
	@URL
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@NotBlank
	@NotNull
	public String getMessageWelcomePage() {
		return this.messageWelcomePage;
	}

	public void setMessageWelcomePage(final String messageWelcomePage) {
		this.messageWelcomePage = messageWelcomePage;
	}

	@NotBlank
	@NotNull
	public String getVATPercentage() {
		return this.VATPercentage;
	}

	public void setVATPercentage(final String vATPercentage) {
		this.VATPercentage = vATPercentage;
	}

	@NotBlank
	@NotNull
	public String getTelephoneCode() {
		return this.telephoneCode;
	}

	public void setTelephoneCode(final String telephoneCode) {
		this.telephoneCode = telephoneCode;
	}

}


package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Picture extends DomainEntity {

	private String	urlPicture;	//url


	//Getters
	@URL
	@NotBlank
	@NotNull
	public String getUrlPicture() {
		return this.urlPicture;
	}

	//Setters
	public void setUrlPicture(final String pic) {
		this.urlPicture = pic;
	}

}

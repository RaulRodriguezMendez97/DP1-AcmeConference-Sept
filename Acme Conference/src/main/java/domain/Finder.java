
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	private String	keyWord;


	//private Collection<Conference>	conferences;

	/*
	 * @ManyToMany
	 * 
	 * @Valid
	 * public Collection<Conference> getConferences() {
	 * return this.conferences;
	 * }
	 * 
	 * public void setConferences(final Collection<Conference> conferences) {
	 * this.conferences = conferences;
	 * }
	 */
	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(final String keyWord) {
		this.keyWord = keyWord;
	}

}

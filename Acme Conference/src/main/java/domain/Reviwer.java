
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Reviwer extends Actor {

	private Collection<String>	keyWords;


	@ElementCollection
	public Collection<String> getKeyWords() {
		return this.keyWords;
	}

	public void setKeyWords(final Collection<String> keyWords) {
		this.keyWords = keyWords;
	}

}

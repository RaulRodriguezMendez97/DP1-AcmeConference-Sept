
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Reviwer extends Actor {

	private Collection<String>		keyWords;
	private Collection<Submission>	submissions;


	@ManyToMany
	@Valid
	public Collection<Submission> getSubmissions() {
		return this.submissions;
	}

	public void setSubmissions(final Collection<Submission> submissions) {
		this.submissions = submissions;
	}

	@ElementCollection
	public Collection<String> getKeyWords() {
		return this.keyWords;
	}

	public void setKeyWords(final Collection<String> keyWords) {
		this.keyWords = keyWords;
	}

}

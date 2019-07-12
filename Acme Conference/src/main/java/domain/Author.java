
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Author extends Actor {

	private Collection<Paper>	papers;


	@ManyToMany
	@Valid
	public Collection<Paper> getPapers() {
		return this.papers;
	}

	public void setPapers(final Collection<Paper> papers) {
		this.papers = papers;
	}

}

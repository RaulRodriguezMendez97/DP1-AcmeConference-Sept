
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Presentation extends Activity {

	private int	vers;


	@Min(0)
	public int getVers() {
		return this.vers;
	}

	public void setVers(final int vers) {
		this.vers = vers;
	}

}

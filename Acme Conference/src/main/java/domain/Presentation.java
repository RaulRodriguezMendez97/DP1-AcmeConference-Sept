
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Presentation extends Activity {

	private int	version;


	@Override
	@Min(0)
	public int getVersion() {
		return this.version;
	}

	@Override
	public void setVersion(final int version) {
		this.version = version;
	}

}

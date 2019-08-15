
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Presentation extends Activity {

	private CamaraReady	camaraReady;


	@NotNull
	@OneToOne(optional = false)
	@Valid
	public CamaraReady getCamaraReady() {
		return this.camaraReady;
	}

	public void setCamaraReady(final CamaraReady camaraReady) {
		this.camaraReady = camaraReady;
	}

}

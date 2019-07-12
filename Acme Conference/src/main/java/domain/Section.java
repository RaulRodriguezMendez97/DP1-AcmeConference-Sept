
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Section extends DomainEntity {

	private int		number;
	private String	title;
	private String	pieceOftext;


	//Getters
	public int getNumber() {
		return this.number;
	}
	@NotBlank
	@NotNull
	public String getTitle() {
		return this.title;
	}

	public String getPieceOfText() {
		return this.pieceOftext;
	}

	//Setters
	public void setNumber(final Integer num) {
		this.number = num;
	}
	public void setTitle(final String tit) {
		this.title = tit;
	}
	public void setPieceOfText(final String piece) {
		this.pieceOftext = piece;
	}
}

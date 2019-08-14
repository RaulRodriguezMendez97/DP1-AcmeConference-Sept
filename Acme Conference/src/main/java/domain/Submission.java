
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Submission extends DomainEntity {

	private String				ticker;
	private Date				moment;
	private int					status;			//0->under-reviwed, 1->rejected, 2->accepted
	private Author				author;
	private Conference			conference;	//1 Obligatoriamente
	private Collection<Reviwer>	reviwers;		// Puede estar vacio
	private Reviwed				reviwed;		//1 Obligatoriamente
	private CamaraReady			camaraReady;	// 0 o 1


	@Valid
	@ManyToMany
	public Collection<Reviwer> getReviwers() {
		return this.reviwers;
	}

	public void setReviwers(final Collection<Reviwer> reviwers) {
		this.reviwers = reviwers;
	}

	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Reviwed getReviwed() {
		return this.reviwed;
	}

	public void setReviwed(final Reviwed reviwed) {
		this.reviwed = reviwed;
	}

	@Valid
	@OneToOne(optional = true)
	public CamaraReady getCamaraReady() {
		return this.camaraReady;
	}

	public void setCamaraReady(final CamaraReady camaraReady) {
		this.camaraReady = camaraReady;
	}

	@ManyToOne(optional = false)
	@NotNull
	@Valid
	public Conference getConference() {
		return this.conference;
	}

	public void setConference(final Conference conference) {
		this.conference = conference;
	}

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(final Author author) {
		this.author = author;
	}

	@NotNull
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}\\-[0-9A-Z]{4}$")
	@Column(unique = true)
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@Range(min = 0, max = 2)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(final int status) {
		this.status = status;
	}

}

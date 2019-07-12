
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	private int			originalityScore;
	private int			qualityScore;
	private int			eadabilityScore;
	private int			decision;
	private int			numbersCommets;
	private Reviwer		reviwer;
	private Submission	submission;


	@ManyToOne(optional = false)
	@NotNull
	@Valid
	public Submission getSubmission() {
		return this.submission;
	}

	public void setSubmission(final Submission submission) {
		this.submission = submission;
	}

	@ManyToOne(optional = false)
	@NotNull
	@Valid
	public Reviwer getReviwer() {
		return this.reviwer;
	}

	public void setReviwer(final Reviwer reviwer) {
		this.reviwer = reviwer;
	}

	@Range(min = 0, max = 10)
	public int getOriginalityScore() {
		return this.originalityScore;
	}

	public void setOriginalityScore(final int originalityScore) {
		this.originalityScore = originalityScore;
	}

	@Range(min = 0, max = 10)
	public int getQualityScore() {
		return this.qualityScore;
	}

	public void setQualityScore(final int qualityScore) {
		this.qualityScore = qualityScore;
	}

	@Range(min = 0, max = 10)
	public int getEadabilityScore() {
		return this.eadabilityScore;
	}

	public void setEadabilityScore(final int eadabilityScore) {
		this.eadabilityScore = eadabilityScore;
	}

	@Range(min = 0, max = 2)
	public int getDecision() {
		return this.decision;
	}

	public void setDecision(final int decision) {
		this.decision = decision;
	}

	@Min(0)
	public int getNumbersCommets() {
		return this.numbersCommets;
	}

	public void setNumbersCommets(final int numbersCommets) {
		this.numbersCommets = numbersCommets;
	}

}

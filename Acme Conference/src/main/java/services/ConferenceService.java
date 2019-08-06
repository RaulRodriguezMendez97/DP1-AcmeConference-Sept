
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ConferenceRepository;
import domain.Conference;

@Service
@Transactional
public class ConferenceService {

	@Autowired
	private ConferenceRepository	conferenceRepository;


	public Collection<Conference> findAll() {
		return this.conferenceRepository.findAll();
	}

	//DASHBOARD

	public List<Object[]> getAvgMinMaxDesvSubmissionsByConference() {
		return this.conferenceRepository.getAvgMinMaxDesvSubmissionsByConference();
	}

	public List<Object[]> getAvgMinMaxDesvRegistrationByConference() {
		return this.conferenceRepository.getAvgMinMaxDesvRegistrationByConference();
	}

	public List<Object[]> getAvgMinMaxDesvFeesByConference() {
		return this.conferenceRepository.getAvgMinMaxDesvFeesByConference();
	}

	public List<Object[]> getAvgMinMaxDesvDaysByConference() {
		return this.conferenceRepository.getAvgMinMaxDesvDaysByConference();
	}

}

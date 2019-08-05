
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
	private ConferenceRepository	conferenceRepsitory;


	public Collection<Conference> findAll() {
		return this.conferenceRepsitory.findAll();
	}

	//DASHBOARD

	public List<Object[]> getAvgMinMaxDesvSubmissionsByConference() {
		return this.conferenceRepsitory.getAvgMinMaxDesvSubmissionsByConference();
	}

	public List<Object[]> getAvgMinMaxDesvFeesByConference() {
		return this.conferenceRepsitory.getAvgMinMaxDesvFeesByConference();
	}

}

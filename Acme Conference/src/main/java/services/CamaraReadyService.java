
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CamaraReadyRepository;
import domain.CamaraReady;

@Service
@Transactional
public class CamaraReadyService {

	@Autowired
	private CamaraReadyRepository	camaraReadyRepository;


	public CamaraReady create() {
		final CamaraReady res = new CamaraReady();

		res.setSummary("");
		res.setTitle("");
		res.setUrlDocument("");

		return res;
	}

	public Collection<CamaraReady> findAll() {
		return this.camaraReadyRepository.findAll();
	}

	public CamaraReady findOne(final int camaraReadyId) {
		return this.camaraReadyRepository.findOne(camaraReadyId);
	}

	public CamaraReady getCameraReadyBySubmission(final Integer id) {
		return this.camaraReadyRepository.getCameraReadyBySubmission(id);
	}

	public CamaraReady save(final CamaraReady camaraReady) {
		final CamaraReady saved = this.camaraReadyRepository.save(camaraReady);
		return saved;
	}
}

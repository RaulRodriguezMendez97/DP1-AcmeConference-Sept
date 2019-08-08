
package services;

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


	public CamaraReady findOne(final int camaraReadyId) {
		return this.camaraReadyRepository.findOne(camaraReadyId);
	}
}

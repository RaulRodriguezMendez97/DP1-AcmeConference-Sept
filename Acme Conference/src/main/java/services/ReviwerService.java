
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ReviwerRepository;
import domain.Reviwer;

@Service
@Transactional
public class ReviwerService {

	@Autowired
	private ReviwerRepository	reviwerRepository;


	public Reviwer getReviwerByUserAccount(final Integer userAccountId) {
		return this.reviwerRepository.getReviwerByUserAccount(userAccountId);
	}

}

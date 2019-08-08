
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ReviwedRepository;
import domain.Reviwed;

@Service
@Transactional
public class ReviwedService {

	@Autowired
	private ReviwedRepository	reviwedRepository;


	public Reviwed findOne(final int reviwedId) {
		return this.reviwedRepository.findOne(reviwedId);
	}
}


package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.AdministratorRepository;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	administratorRepository;


	public Administrator getAdministratorByUserAccount(final Integer userAccountId) {
		return this.administratorRepository.getAdministratorByUserAccount(userAccountId);
	}

}

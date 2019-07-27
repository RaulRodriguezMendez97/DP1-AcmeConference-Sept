
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.AuthorRepository;
import domain.Author;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepository	authorRepository;


	public Author getAuthorByUserAccount(final Integer userAccountId) {
		return this.authorRepository.getAuthorByUserAccount(userAccountId);
	}

}

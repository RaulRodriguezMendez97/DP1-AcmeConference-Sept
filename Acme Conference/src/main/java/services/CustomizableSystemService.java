
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CustomizableSystemRepository;

@Service
@Transactional
public class CustomizableSystemService {

	@Autowired
	private CustomizableSystemRepository	customizableSystemRepository;


	public String getWelcomeMessage() {
		return this.customizableSystemRepository.getWelcomeMessage();
	}

	public String getSpanishWelcomeMessage() {
		return this.customizableSystemRepository.getSpanishWelcomeMessage();
	}

	public String getTelephoneCode() {
		return this.customizableSystemRepository.getTelephoneCode();
	}

	public String getUrlBanner() {
		return this.customizableSystemRepository.getUrlBanner();
	}

	public String getNameApp() {
		return this.customizableSystemRepository.getNameApp();
	}

}

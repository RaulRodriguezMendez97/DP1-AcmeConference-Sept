/*
 * AuthorityToStringConverter.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Tutorial;

@Component
@Transactional
public class TutorialToStringConverter implements Converter<Tutorial, String> {

	@Override
	public String convert(final Tutorial tutorial) {
		String result;

		if (tutorial == null)
			result = null;
		else
			result = String.valueOf(tutorial.getId());

		return result;
	}

}

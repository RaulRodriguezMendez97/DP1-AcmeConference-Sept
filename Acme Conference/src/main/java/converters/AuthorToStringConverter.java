/*
 * ActorToStringConverter.java
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

import domain.Author;

@Component
@Transactional
public class AuthorToStringConverter implements Converter<Author, String> {

	@Override
	public String convert(final Author author) {
		String result;

		if (author == null)
			result = null;
		else
			result = String.valueOf(author.getId());

		return result;
	}

}

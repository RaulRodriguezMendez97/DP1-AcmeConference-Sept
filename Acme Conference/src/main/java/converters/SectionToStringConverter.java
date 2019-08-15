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

import domain.Section;

@Component
@Transactional
public class SectionToStringConverter implements Converter<Section, String> {

	@Override
	public String convert(final Section section) {
		String result;

		if (section == null)
			result = null;
		else
			result = String.valueOf(section.getId());

		return result;
	}

}

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

import domain.Panel;

@Component
@Transactional
public class PanelToStringConverter implements Converter<Panel, String> {

	@Override
	public String convert(final Panel panel) {
		String result;

		if (panel == null)
			result = null;
		else
			result = String.valueOf(panel.getId());

		return result;
	}

}

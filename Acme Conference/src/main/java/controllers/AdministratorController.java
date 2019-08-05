/*
 * AdministratorController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		final ModelAndView result;

		final List<Object[]> getAvgMinMaxDesvSubmissionsByConference = this.conferenceService.getAvgMinMaxDesvSubmissionsByConference();
		final Double getAvgSubmissionsByConference = (Double) getAvgMinMaxDesvSubmissionsByConference.get(0)[0];
		final Double getMinSubmissionsByConference = (Double) getAvgMinMaxDesvSubmissionsByConference.get(0)[1];
		final Double getMaxSubmissionsByConference = (Double) getAvgMinMaxDesvSubmissionsByConference.get(0)[2];

		final List<Object[]> getAvgMinMaxDesvFeesByConference = this.conferenceService.getAvgMinMaxDesvFeesByConference();
		final Double getAvgFeesByConference = (Double) getAvgMinMaxDesvFeesByConference.get(0)[0];
		final Double getMinFeesByConference = (Double) getAvgMinMaxDesvFeesByConference.get(0)[1];
		final Double getMaxFeesByConference = (Double) getAvgMinMaxDesvFeesByConference.get(0)[2];
		final Double getDesvFeesByConference = (Double) getAvgMinMaxDesvFeesByConference.get(0)[3];

		result = new ModelAndView("administrator/dashboard");

		result.addObject("getAvgFeesByConference", getAvgFeesByConference);
		result.addObject("getMinFeesByConference", getMinFeesByConference);
		result.addObject("getMaxFeesByConference", getMaxFeesByConference);
		result.addObject("getDesvFeesByConference", getDesvFeesByConference);

		result.addObject("getAvgSubmissionsByConference", getAvgSubmissionsByConference);
		result.addObject("getMinSubmissionsByConference", getMinSubmissionsByConference);
		result.addObject("getMaxSubmissionsByConference", getMaxSubmissionsByConference);

		return result;
	}
}

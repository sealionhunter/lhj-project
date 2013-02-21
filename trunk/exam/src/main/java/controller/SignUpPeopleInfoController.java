package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import service.RegistService;

import command.RegistCommand;

public class SignUpPeopleInfoController extends SimpleFormController {

	private RegistService registService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#
	 * referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object,
	 * org.springframework.validation.Errors, int)
	 */
	@Override
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors) throws Exception {

		RegistCommand cmd = (RegistCommand) command;
		cmd.setApplyUsers(registService.listApplyUser());
		return super.referenceData(request, command, errors);
	}


	/**
	 * @return the registService
	 */
	public RegistService getRegistService() {
		return registService;
	}

	/**
	 * @param registService
	 *            the registService to set
	 */
	public void setRegistService(RegistService registService) {
		this.registService = registService;
	}

}
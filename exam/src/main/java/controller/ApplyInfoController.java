package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.SimpleFormController;

import service.RegistService;

import command.RegistCommand;

public class ApplyInfoController extends SimpleFormController {

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
        cmd.setOffices(registService.listOffice());
        cmd.setDeparts(registService.listDepart());
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
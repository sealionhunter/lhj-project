package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import service.RegistService;

import command.AdminModifyCommand;

public class AdminModifyController extends SimpleFormController {
    private RegistService registService;

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

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        AdminModifyCommand cmd = (AdminModifyCommand) command;
        if (!errors.hasErrors()) {
            registService.modifyAdminPassword(cmd, errors);
        }
        if (errors.hasErrors()) {
            return new ModelAndView(getFormView(), errors.getModel());
        } else {
            return new ModelAndView(getSuccessView(), errors.getModel());
        }
    }
}

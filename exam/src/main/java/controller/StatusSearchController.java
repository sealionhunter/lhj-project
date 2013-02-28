package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Apply;
import model.User;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import service.RegistService;

import command.StatusSearchCommand;

public class StatusSearchController extends SimpleFormController {
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
        StatusSearchCommand cmd = (StatusSearchCommand) command;
        String submitType = cmd.getSubmitType();
        if ("search".equals(submitType)) {
            cmd.setShowDetail(false);
            if (!errors.hasErrors()) {
                registService.searchStatus(cmd, errors);
            }
            if (errors.hasErrors()) {
                return new ModelAndView(getFormView(), errors.getModel());
            }
            HttpSession session = request.getSession();
            session.setAttribute("UserInfo", cmd.getUser());
            session.setAttribute("ApplyInfo", cmd.getApply());
            cmd.setShowDetail(true);
            request.getSession().setAttribute("photoData" + cmd.getIdCardNo(),
                    cmd.getUser().getPhoto());
            return new ModelAndView(getFormView(), errors.getModel());
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("UserInfo");
            Apply apply = (Apply) session.getAttribute("ApplyInfo");
            cmd.setUser(user);
            cmd.setApply(apply);
            return new ModelAndView(getSuccessView(), errors.getModel());
        }
    }
}

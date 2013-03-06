package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Apply;
import model.User;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import service.UserPasswordService;

import command.UserPasswordResetCommand;

public class UserPasswordResetController extends SimpleFormController {
    private UserPasswordService userPasswordService;
    private String initPassword = null;

    public UserPasswordService getUserPasswordService() {
        return userPasswordService;
    }

    public void setUserPasswordService(UserPasswordService userPasswordService) {
        this.userPasswordService = userPasswordService;
    }

    public String getInitPassword() {
        return initPassword;
    }

    public void setInitPassword(String initPassword) {
        this.initPassword = initPassword;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        UserPasswordResetCommand cmd = (UserPasswordResetCommand) command;
        String submitType = cmd.getSubmitType();
        if ("search".equals(submitType)) {
            cmd.setShowDetail(false);
            if (!errors.hasErrors()) {
                userPasswordService.searchUserApplyInfo(cmd, errors);
                HttpSession session = request.getSession();
                session.setAttribute("UserInfo", cmd.getUser());
                session.setAttribute("ApplyInfo", cmd.getApply());
            }
            if (errors.hasErrors()) {
                return new ModelAndView(getFormView(), errors.getModel());
            }
            cmd.setShowDetail(true);
            request.getSession().setAttribute("photoData" + cmd.getIdCardNo(),
                    cmd.getUser().getPhoto());
            return new ModelAndView(getFormView(), errors.getModel());
        } else if ("reset".equals(submitType)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("UserInfo");
            Apply apply = (Apply) session.getAttribute("ApplyInfo");
            cmd.setUser(user);
            cmd.setApply(apply);
            cmd.setShowDetail(true);
            userPasswordService.resetUserPasswor(cmd.getIdCardNo(),
                    initPassword);
            Map model = errors.getModel();
            model.put("alert", true);
            return new ModelAndView(getFormView(), model);
        } else {
            HttpSession session = request.getSession();
            session.removeAttribute("UserInfo");
            session.removeAttribute("ApplyInfo");
            session.removeAttribute("photoData" + cmd.getIdCardNo());
            return new ModelAndView(new RedirectView("adminInit.action"));
        }
    }
}

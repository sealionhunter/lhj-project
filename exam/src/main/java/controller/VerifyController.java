package controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Apply;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import service.RegistService;

import command.VerifyCommand;

public class VerifyController extends SimpleFormController {
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

    @SuppressWarnings("rawtypes")
    @Override
    protected Map referenceData(HttpServletRequest request, Object command,
            Errors errors) throws Exception {
        VerifyCommand cmd = (VerifyCommand) command;
        String userId = request.getParameter("userId");
        if (userId != null && !userId.isEmpty()) {
            cmd.setUserId(Integer.valueOf(userId));
        }
        registService.initVerify(cmd);
        request.getSession().setAttribute(
                "photoData" + cmd.getUser().getIdCardNo(),
                cmd.getUser().getPhoto());

        return super.referenceData(request, command, errors);
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        VerifyCommand cmd = (VerifyCommand) command;
        String reason = cmd.getVerifyReason();
        Integer state = Integer.valueOf(cmd.getVerifyState());
        boolean submitFlag = Boolean.valueOf(cmd.getSubmitFlag());
        if (!submitFlag) {
            // back
            return new ModelAndView(new RedirectView("signuppeopleinfo.action"));
        }
        registService.initVerify(cmd);
        Apply apply = cmd.getApply();
        apply.setReason(reason);
        apply.setState(state);
        apply.setUdpateDate(new Date());

        registService.verify(apply);

        return new ModelAndView(new RedirectView("signuppeopleinfo.action"));
    }
}

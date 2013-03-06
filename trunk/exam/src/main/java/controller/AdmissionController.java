package controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Apply;
import model.Exam;
import model.User;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import service.RegistService;

import command.AdmissionCommand;

public class AdmissionController extends SimpleFormController {
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.SimpleFormController#referenceData
     * (javax.servlet.http.HttpServletRequest, java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected Map referenceData(HttpServletRequest request, Object command,
            Errors errors) throws Exception {
        Exam exam = registService.listExam().get(0);
        Date today = Calendar.getInstance().getTime();
        Date start = exam.getAdmissionPrintStart() == null ? today : exam
                .getAdmissionPrintStart();
        Date end = exam.getAdmissionPrintEnd() == null ? today : exam
                .getAdmissionPrintEnd();
        if (today.before(start) || today.after(end)) {
            throw new Exception("当前时间段不在打印时间内，请在规定时间段内打印笔试准考证！");
        }
        return super.referenceData(request, command, errors);
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        AdmissionCommand cmd = (AdmissionCommand) command;
        String submitType = cmd.getSubmitType();
        if ("search".equals(submitType)) {
            if (!errors.hasErrors()) {
                registService.searchStatus(cmd, errors);
            }
            if (errors.hasErrors()) {
                return new ModelAndView(getFormView(), errors.getModel());
            }
            HttpSession session = request.getSession();
            session.setAttribute("UserInfo", cmd.getUser());
            session.setAttribute("ApplyInfo", cmd.getApply());
            session.setAttribute("photoData" + cmd.getIdCardNo(), cmd.getUser()
                    .getPhoto());
            return new ModelAndView(getFormView(), errors.getModel());
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("UserInfo");
            Apply apply = (Apply) session.getAttribute("ApplyInfo");
            cmd.setUser(user);
            cmd.setApply(apply);
            registService.printAdmission(cmd, errors);
            return new ModelAndView(getSuccessView(), errors.getModel());
        }
    }
}

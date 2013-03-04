package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Apply;
import model.User;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import service.RegistService;

import command.AdmissionCommand;
import command.RegistCommand;

public class AdmissionController extends SimpleFormController {
    private RegistService registService;
    private String beginDate;
    private String endDate;

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
    protected Map referenceData(HttpServletRequest request, Object command,
            Errors errors) throws Exception {
        Date now = new Date();
        String pattern = "yyyy/MM/dd HH:mm:ss";
        if (now.after(getEndTime(pattern)) || now.before(getBeginTime(pattern))) {
            throw new Exception("当前时间段不在打印时间内！");
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

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    private Date getBeginTime(String pattern) throws Exception {
        String timeStr = " 00:00:00";

        return getTime(beginDate + timeStr, pattern);
    }

    private Date getEndTime(String pattern) throws Exception {
        String timeStr = " 23:59:59";

        return getTime(endDate + timeStr, pattern);
    }

    private Date getTime(String timeStr, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.parse(timeStr);
    }
}

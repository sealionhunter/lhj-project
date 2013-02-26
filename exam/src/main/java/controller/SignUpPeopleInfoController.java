package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Apply;
import model.Depart;
import model.Office;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import service.RegistService;

import command.SignUpPersonSearchCommand;

public class SignUpPeopleInfoController extends SimpleFormController {

    private RegistService registService;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#
     * referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object,
     * org.springframework.validation.Errors, int)
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected Map referenceData(HttpServletRequest request, Object command,
            Errors errors) throws Exception {
        SignUpPersonSearchCommand cmd = (SignUpPersonSearchCommand) command;
        cmd.setVerifyUserId(null);
        Map model = super.referenceData(request, command, errors);
        if (model == null) {
            model = new HashMap();
        }
        initModel(model, cmd);

        return model;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception {
        HttpSession session = request.getSession();
        Object cmd = session.getAttribute(getFormSessionAttributeName(request));
        if (cmd != null) {
            return cmd;
        }
        return super.formBackingObject(request);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        SignUpPersonSearchCommand cmd = (SignUpPersonSearchCommand) command;
        request.getSession().setAttribute(getFormSessionAttributeName(), cmd);
        if (cmd.getVerifyUserId() == null || cmd.getVerifyUserId().isEmpty()) {

            Map model = errors.getModel();
            initModel(model, cmd);
            return new ModelAndView(getFormView(), model);
        } else {
            String url = "verify.action?userId=" + cmd.getVerifyUserId();
            return new ModelAndView(new RedirectView(url));
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initModel(Map model, SignUpPersonSearchCommand cmd)
            throws Exception {
        List<Apply> applyList = registService.listApplyUser(cmd);
        model.put("applyUsers", applyList);

        List<Depart> deptList = registService.listDepart();

        Depart dept = new Depart();
        dept.setId(-1);
        dept.setName("");
        deptList.add(0, dept);
        model.put("departs", deptList);

        List<Office> officeList = registService.listOffice();
        Office office = new Office();
        office.setId(-1);
        office.setName("");
        office.setCode("");
        officeList.add(0, office);
        model.put("offices", officeList);
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
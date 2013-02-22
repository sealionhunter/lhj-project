package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Apply;
import model.Depart;
import model.Office;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

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
    @Override
    protected Map referenceData(HttpServletRequest request, Object command,
            Errors errors) throws Exception {
        Map model = super.referenceData(request, command, errors);
        if (model == null) {
            model = new HashMap();
        }
        initModel(model, -1, -1);

        return model;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        SignUpPersonSearchCommand cmd = (SignUpPersonSearchCommand) command;
        Integer deptId = cmd.getDeptId();
        Integer officeId = cmd.getPostId();

        Map model = errors.getModel();
        initModel(model, deptId, officeId);
        return new ModelAndView(getFormView(), model);
    }

    private void initModel(Map model, Integer deptId, Integer officeId) throws Exception {
        List<Apply> applyList = registService.listApplyUser(deptId, officeId);
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
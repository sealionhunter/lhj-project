package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Apply;
import model.Depart;
import model.Master;
import model.MasterPK;
import model.Office;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import service.RegistService;

import command.SignUpPersonSearchCommand;
import command.SignupDetailSearchCommand;

public class SignupDetailSearchController extends SimpleFormController {

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
        SignupDetailSearchCommand cmd = (SignupDetailSearchCommand) command;
        Map model = super.referenceData(request, command, errors);
        if (model == null) {
            model = new HashMap();
        }
        initModel(model, cmd);

        return model;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        SignupDetailSearchCommand cmd = (SignupDetailSearchCommand) command;
        Map model = errors.getModel();
        initModel(model, cmd);
        return new ModelAndView(getFormView(), model);
    }

    private void initModel(Map model, SignupDetailSearchCommand cmd)
            throws Exception {
        List<Apply> applyList = registService.searchApplyUsers(cmd);
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

        List<Master> politicalCodes = registService.getMasters(3);
        Master master = new Master();
        MasterPK id = new MasterPK();
        id.setCategory(3);
        id.setCode(-1);
        master.setName("");
        master.setId(id);
        politicalCodes.add(0, master);

        model.put("politicalCodes", politicalCodes);
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
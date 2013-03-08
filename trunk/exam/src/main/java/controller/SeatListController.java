package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Apply;
import model.Depart;
import model.Master;
import model.MasterPK;
import model.Office;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import service.RegistService;

import command.SignUpPersonSearchCommand;

public class SeatListController extends SimpleFormController {

    private RegistService registService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
     * (javax.servlet.http.HttpServletRequest)
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
        cmd.setState(2);
        Map model = super.referenceData(request, command, errors);
        if (model == null) {
            model = new HashMap();
        }
        initModel(model, cmd);

        return model;
    }

    @SuppressWarnings({ "rawtypes" })
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        SignUpPersonSearchCommand cmd = (SignUpPersonSearchCommand) command;
        cmd.setState(2);
        request.getSession().setAttribute(getFormSessionAttributeName(), cmd);
        Map model = errors.getModel();
        initModel(model, cmd);
        model.remove("downloadFile");
        return new ModelAndView(getFormView(), model);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initModel(Map model, SignUpPersonSearchCommand cmd)
            throws Exception {

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

        List<Master> identities = registService.getMasters(4);
        Master master2 = new Master();
        MasterPK id2 = new MasterPK();
        id2.setCategory(4);
        id2.setCode(-1);
        master2.setName("");
        master2.setId(id);
        identities.add(0, master2);
        model.put("identities", identities);

        // if ((cmd.getDeptId() == null || cmd.getDeptId() == -1)
        // && deptList != null && deptList.size() > 0) {
        // cmd.setDeptId(deptList.get(0).getId());
        // }
        // if ((cmd.getPostId() == null || cmd.getPostId() == -1)
        // && officeList != null && officeList.size() > 0) {
        // cmd.setPostId(officeList.get(0).getId());
        // }

        List<Apply> applyList = registService.listApplyUser(cmd);
        model.put("applyUsers", applyList);
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
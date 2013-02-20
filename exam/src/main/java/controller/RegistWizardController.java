package controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Apply;
import model.Depart;
import model.Exam;
import model.Office;
import model.User;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import service.RegistService;
import validator.RegistValidator;

import command.RegistCommand;

public class RegistWizardController extends AbstractWizardFormController {

    private RegistService registService;

    private String successView;
    private String cancelView;

    public String getCancelView() {
        return cancelView;
    }

    public void setCancelView(String cancelView) {
        this.cancelView = cancelView;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

//    /*
//     * (non-Javadoc)
//     * 
//     * @see
//     * org.springframework.web.servlet.mvc.AbstractWizardFormController#showForm
//     * (javax.servlet.http.HttpServletRequest,
//     * javax.servlet.http.HttpServletResponse,
//     * org.springframework.validation.BindException)
//     */
//    @Override
//    protected ModelAndView showForm(HttpServletRequest request,
//            HttpServletResponse response, BindException errors)
//            throws Exception {
//
//        ModelAndView mv = super.showForm(request, response, errors);
//        if (!isFormSubmission(request) && errors.hasGlobalErrors() && getCurrentPage(request) == getInitialPage(request)
//                || isFormSubmission(request) && errors.hasGlobalErrors() && getCurrentPage(request) == 0) {
//            Map m = new HashMap();
//            m.put("errorMsg",
//                    errors.getGlobalError().getDefaultMessage());
//            return new ModelAndView("regist/examDateExceed", m);
//        }
//        return mv;
//    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#
     * referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object,
     * org.springframework.validation.Errors, int)
     */
    @Override
    protected Map referenceData(HttpServletRequest request, Object command,
            Errors errors, int page) throws Exception {
        RegistCommand cmd = (RegistCommand) command;
        if (page == 0) {
            String editFlg = request.getParameter("editFlg");
            if (editFlg != null && !editFlg.isEmpty()) {
                cmd.setEditFlg(editFlg);
            }
            cmd.setExams(registService.listExam());
            Date today = Calendar.getInstance().getTime();
            for (Exam exam : cmd.getExams()) {
                if (!"1".equals(editFlg) && today.after(exam.getApplyDeadDate())) {
                    throw new Exception("很抱歉，报名已经结束！");
//                    errors.reject("test", "很抱歉，报名已经结束！");
                } else if (today.before(exam.getApplyBeginDate())) {
                    throw new Exception("报名还未开始，请耐心等待！");
//                    errors.reject("test", "报名还未开始，请耐心等待！");
                }
            }
            if (!errors.hasErrors()) {
                cmd.setSexs(registService.getMasters(1));
                cmd.setMarriedStates(registService.getMasters(2));
                cmd.setPoliticalCodes(registService.getMasters(3));
                cmd.setIdentities(registService.getMasters(4));
                cmd.setNationCodes(registService.getMasters(5));
                cmd.setDeparts(registService.listDepart());
                cmd.setOffices(registService.listOffice());
                cmd.setCities(registService.listCity());
            }
        }
        return super.referenceData(request, command, errors, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#
     * postProcessPage(javax.servlet.http.HttpServletRequest, java.lang.Object,
     * org.springframework.validation.Errors, int)
     */
    @Override
    protected void postProcessPage(HttpServletRequest request, Object command,
            Errors errors, int page) throws Exception {
        RegistCommand cmd = (RegistCommand) command;
        if (page == 0 && !errors.hasErrors()) {
            String idCardNo = cmd.getIdCardNo();
            List<User> users = registService.findIdCardNo(idCardNo);
            if (users != null && !users.isEmpty()) {
                User user = users.get(0);
                cmd.setUserId(user.getId());
                cmd.setName(user.getName());
                cmd.setBirthdayMonth(user.getBirthdayMonth());
                cmd.setBirthdayYear(user.getBirthdayYear());
                cmd.setIdCardNo(user.getIdCardNo());
                cmd.setPassword(user.getPassword());
                cmd.setBirthdayMonth(user.getBirthdayMonth());
                cmd.setBirthdayYear(user.getBirthdayYear());
                cmd.setComputerSkill(user.getComputerSkill());
                cmd.setLanguageSkill(user.getComputerSkill());
                cmd.setDegree(user.getDegree());
                cmd.setGraduateMonth(String.valueOf(user.getGraduateMonth()));
                cmd.setGraduateYear(String.valueOf(user.getGraduateYear()));
                cmd.setUserSchool(user.getGraduateSchool());
                cmd.setHeight(String.valueOf(user.getHeight()));
                cmd.setHomeTown(user.getHomeTown());
                cmd.setIdCardNo(user.getIdCardNo());
                cmd.setIdentity(user.getIdentity());
                cmd.setBirthdayMonth(user.getBirthdayMonth());
                cmd.setBirthdayMonth(user.getBirthdayMonth());
                cmd.setBirthdayMonth(user.getBirthdayMonth());
                cmd.setBirthdayMonth(user.getBirthdayMonth());
                cmd.setUserMajor(user.getMajor());
                cmd.setTelephone(user.getTelephone());
                cmd.setWorkYears(String.valueOf(user.getWorkyears()));
                cmd.setTrainingExp(user.getTrainingExp());
                cmd.setWorkExp(user.getWorkExp());
                cmd.setSocialRel(user.getSocialRel());
                List<Apply> applies = registService.getApply(user.getId());
                if (applies != null && !applies.isEmpty()) {
                    Integer applyPostId = applies.get(0).getId().getOfficeid();
                    int state = applies.get(0).getState();
                    if (state == 2) {
                        throw new Exception("申请已审核完毕，无法修改！");
//                        errors.reject("error", "");
//                        super.postProcessPage(request, command, errors, page);
//                        return ;
                    }
                    if (applyPostId != null) {
                        Office office = null;
                        for (Office dbOffice : cmd.getOffices()) {
                            if (dbOffice.getId().equals(applyPostId)) {
                                office = dbOffice;
                            }
                        }
                        cmd.setApplyPostId(String.valueOf(applyPostId));
                        // Office office = registService.getOffice(applyPostId);
                        if (office != null) {
                            cmd.setApplyPostCode(office.getCode());
                            cmd.setApplyDeptId(String.valueOf(office
                                    .getDepartId()));
                            cmd.setApplyDeptName(office.getDepartName());
                            for (Exam dbExam : cmd.getExams()) {
                                if (dbExam.getId().equals(office.getExamId())) {
                                    cmd.setRegistExamCode(String.valueOf(dbExam
                                            .getId()));
                                    cmd.setRegistExamName(dbExam.getName());
                                    cmd.setRegistExamLocation(dbExam
                                            .getExamPosition());
                                    break;
                                }
                            }
                        }
                    }

                }

                request.getSession().setAttribute(
                        "photoData" + cmd.getIdCardNo(), user.getPhoto());
            }
        } else if (page == 1) {
            String examId = cmd.getRegistExamCode();
            for (Exam exam : cmd.getExams()) {
                if (examId.equals(String.valueOf(exam.getId()))) {
                    cmd.setRegistExamName(exam.getName());
                    cmd.setRegistExamLocation(exam.getExamPosition());
                }
            }
        } else if (page == 4) {
            String departId = cmd.getApplyDeptId();
            for (Depart depart : cmd.getDeparts()) {
                if (departId.equals(String.valueOf(depart.getId()))) {
                    // cmd.setApplyCityId(depart.getCityName());
                    cmd.setApplyDeptName(depart.getName());
                }
            }
            String officeId = cmd.getApplyPostId();
            for (Office office : cmd.getOffices()) {
                if (officeId.equals(String.valueOf(office.getId()))) {
                    cmd.setApplyPostName(office.getName());
                    cmd.setApplyPostCode(office.getCode());
                }
            }
        } else if (page == 5) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest
                    .getFile("uploadPhoto");
            if (multipartFile != null && !multipartFile.isEmpty()) {
                if (multipartFile.getSize() > 102400) {
                    errors.reject("uploadPhoto", "上传的图片太大");
                } else {

                    // InputStream in = multipartFile.getInputStream();
                    // ByteArrayOutputStream out = new ByteArrayOutputStream();
                    // byte[] buff = new byte[8192];
                    // int len = 0;
                    // while ((len = in.read(buff, 0, 8192)) != -1) {
                    // out.write(buff, 0, len);
                    // }
                    // out.flush();
                    // out.close();
                    // in.close();

                    cmd.setPhoto(multipartFile.getBytes());
                    request.getSession().setAttribute(
                            "photoData" + cmd.getIdCardNo(), cmd.getPhoto());
                }

            }
        }
        super.postProcessPage(request, command, errors, page);
    }

    protected ModelAndView processCancel(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, BindException arg3)
            throws Exception {

        // registService.add(null);
        return new ModelAndView(this.getCancelView());
    }

    protected ModelAndView processFinish(HttpServletRequest request,
            HttpServletResponse response, Object object, BindException exception)
            throws Exception {
        RegistCommand vote = (RegistCommand) object;

        registService.add(vote);
        return new ModelAndView(this.getSuccessView(), "vote", vote);
    }

    protected void validatePage(Object command, Errors errors, int page) {
        RegistCommand vote = (RegistCommand) command;
        RegistValidator validator = (RegistValidator) getValidator();

        switch (page) {
        case 0:
            List<User> idCardNoLst = null;
            try {
                if (vote.getIdCardNo() != null) {
                    idCardNoLst = registService
                            .findIdCardNo(vote.getIdCardNo());
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            validator.validateRegistInfo(vote, errors, idCardNoLst);
            break;
        case 1:
            validator.validateId(vote, errors);
            break;
        case 3:
            validator.validatePeopleInfo(vote, errors);
            break;
        default:
            return;
        }

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
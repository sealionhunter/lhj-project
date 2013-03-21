package service;

import org.springframework.validation.BindException;

import model.Admission;

import command.ScoreInputCommand;

import dao.AdmissionDao;

public class ScoreServiceImpl implements ScoreService {
    private AdmissionDao admissionDao;

    /**
     * @return the admissionDao
     */
    public AdmissionDao getAdmissionDao() {
        return admissionDao;
    }

    /**
     * @param admissionDao
     *            the admissionDao to set
     */
    public void setAdmissionDao(AdmissionDao admissionDao) {
        this.admissionDao = admissionDao;
    }

    @Override
    public void inputScore(ScoreInputCommand cmd, BindException errors) throws Exception {
        Admission admission = admissionDao.get(cmd.getIdCardNo(), cmd.getAdmissionId());
        if (admission == null) {
            errors.rejectValue("idCardNo", "idCardNo.error", "输入的身份证号与准考证号不匹配，请确认后重新输入。");
            return;
        }
        double score = Double.parseDouble(cmd.getScore());
        admission.setScore(score);
        admissionDao.update(admission);
    }

}

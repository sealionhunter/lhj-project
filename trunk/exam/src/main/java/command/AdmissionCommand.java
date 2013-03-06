package command;

import model.Admission;
import model.Exam;

public class AdmissionCommand extends StatusSearchCommand {
    private Admission admission = null;
    private Exam exam = null;

    /**
     * @return the admission
     */
    public Admission getAdmission() {
        return admission;
    }

    /**
     * @param admission
     *            the admission to set
     */
    public void setAdmission(Admission admission) {
        this.admission = admission;
    }

    /**
     * @return the exam
     */
    public Exam getExam() {
        return exam;
    }

    /**
     * @param exam
     *            the exam to set
     */
    public void setExam(Exam exam) {
        this.exam = exam;
    }
}

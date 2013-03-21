package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import service.ScoreService;

import command.ScoreInputCommand;

public class ScoreInputController extends SimpleFormController {
    private ScoreService scoreService;

    /**
     * @return the scoreService
     */
    public ScoreService getScoreService() {
        return scoreService;
    }

    /**
     * @param scoreService
     *            the scoreService to set
     */
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        ScoreInputCommand cmd = (ScoreInputCommand) command;
        Boolean flag = Boolean.valueOf(cmd.getSubmitFlag());
        if (!flag) {
            return new ModelAndView(new RedirectView("adminInit.action"));
        }
        if (!errors.hasErrors()) {
            scoreService.inputScore(cmd, errors);
        }
        if (errors.hasErrors()) {
            return new ModelAndView(getFormView(), errors.getModel());
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.putAll(errors.getModel());
        model.put("success", true);
        cmd.setAdmissionId("");
        cmd.setIdCardNo("");
        cmd.setScore("");
        return new ModelAndView(getSuccessView(), model);
    }
}

package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import service.RoomService;

import command.RoomEditCommand;

public class RoomListController extends SimpleFormController {

    private RoomService roomService;

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
        RoomEditCommand cmd = (RoomEditCommand) command;
        return roomService.initList(cmd);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        RoomEditCommand cmd = (RoomEditCommand) command;
        Map model = errors.getModel();
        if (request.getParameter("generateAdmission") != null) {
            roomService.generateAdmission(request, cmd, errors);
            return new ModelAndView(getSuccessView(), model);
        }
        model.putAll(roomService.initList(cmd));
        return new ModelAndView(getFormView(), model);
    }

    /**
     * @return the roomService
     */
    public RoomService getRoomService() {
        return roomService;
    }

    /**
     * @param roomService
     *            the roomService to set
     */
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

}
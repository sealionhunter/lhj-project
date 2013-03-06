package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import service.RoomService;

import command.RoomEditCommand;

public class RoomEditController extends SimpleFormController {

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
        String id = request.getParameter("roomId");
        if (id != null && id.length() > 0) {
            try {
                cmd.setId(Integer.valueOf(id));
            } catch (Exception e) {
            }
        }
        return roomService.initEdit(cmd, id, errors);
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        RoomEditCommand cmd = (RoomEditCommand) command;
        roomService.editOk(request, cmd, errors);
        if (errors.hasErrors()) {
            return new ModelAndView(getFormView(), errors.getModel());
        }
        return new ModelAndView(new RedirectView("roomList.action"),
                errors.getModel());
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
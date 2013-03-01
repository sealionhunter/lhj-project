package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import service.RoomService;

import command.UserSeatResetCommand;

public class UserSeatResetController extends SimpleFormController {

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

    private RoomService roomService;

    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService RoomService) {
        this.roomService = RoomService;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        UserSeatResetCommand cmd = (UserSeatResetCommand) command;
        request.getSession().setAttribute(getFormSessionAttributeName(), cmd);
        cmd.setShowDetail(false);
        if (request.getParameter("search") != null) {
            if (!errors.hasErrors()) {
                roomService.initSeatReset(cmd, errors);
            }
            if (errors.hasErrors()) {
                return new ModelAndView(getFormView(), errors.getModel());
            }
            cmd.setShowDetail(true);
            request.getSession().setAttribute("photoData" + cmd.getIdCardNo(),
                    cmd.getUser().getPhoto());
            return new ModelAndView(getFormView(), errors.getModel());
        } else if (request.getParameter("reset") != null) {
            cmd.setShowDetail(true);
            roomService.resetUserSeat(cmd, errors);
            return new ModelAndView(getFormView(), errors.getModel());
        } else {
            request.getSession().removeAttribute(getFormSessionAttributeName());
            return new ModelAndView(new RedirectView("adminInit.action"));
        }
    }
}

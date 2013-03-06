package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import command.RoomEditCommand;
import command.UserSeatResetCommand;

public interface RoomService {

    @SuppressWarnings("rawtypes")
    public Map initEdit(RoomEditCommand cmd, String id, Errors errors)
            throws Exception;

    @SuppressWarnings("rawtypes")
    public Map initList(RoomEditCommand cmd) throws Exception;

    @SuppressWarnings("rawtypes")
    public Map initRoomAssign(RoomEditCommand cmd, Errors errors)
            throws Exception;

    public void editOk(HttpServletRequest request, RoomEditCommand cmd,
            BindException errors) throws Exception;

    public void generateAdmission(HttpServletRequest request,
            RoomEditCommand cmd, BindException errors) throws Exception;

    public void resetUserSeat(UserSeatResetCommand cmd, BindException errors)
            throws Exception;

    public void initSeatReset(UserSeatResetCommand cmd, BindException errors)
            throws Exception;

    public void generateRoom(HttpServletRequest request, RoomEditCommand cmd,
            BindException errors) throws Exception;

    public void deleteRoom(HttpServletRequest request, RoomEditCommand cmd,
            BindException errors) throws Exception;

    public void removeAssign(HttpServletRequest request, RoomEditCommand cmd,
            BindException errors) throws Exception;

    public void assignRoom(HttpServletRequest request, RoomEditCommand cmd,
            BindException errors) throws Exception;

}

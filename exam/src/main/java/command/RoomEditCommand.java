package command;

import java.util.List;

import model.Depart;
import model.Office;

public class RoomEditCommand {
    private Integer id;

    private String code;

    private String name;

    private Integer departId;

    private Integer officeId;

    private String position;

    private String seats;

    private List<Depart> departs;

    private List<Office> offices;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the departId
     */
    public Integer getDepartId() {
        return departId;
    }

    /**
     * @param departId
     *            the departId to set
     */
    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    /**
     * @return the officeId
     */
    public Integer getOfficeId() {
        return officeId;
    }

    /**
     * @param officeId
     *            the officeId to set
     */
    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position
     *            the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the seats
     */
    public String getSeats() {
        return seats;
    }

    /**
     * @param seats
     *            the seats to set
     */
    public void setSeats(String seats) {
        this.seats = seats;
    }

    /**
     * @return the departs
     */
    public List<Depart> getDeparts() {
        return departs;
    }

    /**
     * @param departs
     *            the departs to set
     */
    public void setDeparts(List<Depart> departs) {
        this.departs = departs;
    }

    /**
     * @return the offices
     */
    public List<Office> getOffices() {
        return offices;
    }

    /**
     * @param offices
     *            the offices to set
     */
    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }
}

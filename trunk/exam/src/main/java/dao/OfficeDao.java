package dao;

import java.util.List;

import model.Office;

public interface OfficeDao {

    public Office get(int id) throws Exception;

    public List<Office> list() throws Exception;

}

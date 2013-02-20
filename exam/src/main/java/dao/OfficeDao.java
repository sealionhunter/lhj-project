package dao;

import java.util.List;

import model.Office;

public interface OfficeDao {

    // public void add(User user) throws Exception;

    public Office get(int id) throws Exception;

    public List<Office> list() throws Exception;
    // public void delete(int id) throws Exception;
    //
    // public void update(User user) throws Exception;

}

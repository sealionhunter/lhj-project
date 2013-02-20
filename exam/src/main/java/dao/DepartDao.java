package dao;

import java.util.List;

import model.Depart;

public interface DepartDao {

    // public void add(User user) throws Exception;

    public Depart get(int id) throws Exception;

    public List<Depart> list() throws Exception;
    // public void delete(int id) throws Exception;
    //
    // public void update(User user) throws Exception;

}

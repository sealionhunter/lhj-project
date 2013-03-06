package dao;

import java.util.List;

import model.Depart;

public interface DepartDao {

    public Depart get(int id) throws Exception;

    public List<Depart> list() throws Exception;

}

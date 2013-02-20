package dao;

import java.util.List;

import model.City;

public interface CityDao {

//    public void add(User user) throws Exception;

    public City get(int id) throws Exception;

     public List<City> list() throws Exception;
//    public void delete(int id) throws Exception;
//
//    public void update(User user) throws Exception;

}

package dao;

import java.util.List;

import model.City;

public interface CityDao {
    public City get(int id) throws Exception;

    public List<City> list() throws Exception;

}

package dao;

import java.util.List;

import model.Master;

public interface MasterDao {

    public List<Master> list(int category) throws Exception;

}

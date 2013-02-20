package dao;

import java.util.List;

import model.Master;

public interface MasterDao {

    // public void add(User user) throws Exception;

//    public Master get() throws Exception;

    public List<Master> list(int category) throws Exception;
    // public void delete(int id) throws Exception;
    //
    // public void update(User user) throws Exception;

}

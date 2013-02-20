package dao;

import java.util.List;

import model.User;

public interface UserDao {

    public Integer add(User user) throws Exception;

    public List<User> findIdCardNo(String idcardno) throws Exception;
    public List<User> findUserName(String userName) throws Exception;
    public User get(int id) throws Exception;

    // public List<User> findUser() throws Exception;
    public void delete(int id) throws Exception;

    public void update(User user) throws Exception;

}

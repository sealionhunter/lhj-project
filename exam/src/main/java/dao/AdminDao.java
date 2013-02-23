package dao;

import model.Admin;

public interface AdminDao {
    public Admin get(String adminId) throws Exception;

    public void update(Admin admin) throws Exception;
}

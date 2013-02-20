package dao;

import java.util.List;

import model.Exam;

public interface ExamDao {

    // public void add(User user) throws Exception;

    public Exam get(int id) throws Exception;

    public List<Exam> list() throws Exception;
    // public void delete(int id) throws Exception;
    //
    // public void update(User user) throws Exception;

}

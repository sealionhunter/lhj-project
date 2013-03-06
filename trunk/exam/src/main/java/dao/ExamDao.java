package dao;

import java.util.List;

import model.Exam;

public interface ExamDao {

    public Exam get(int id) throws Exception;

    public List<Exam> list() throws Exception;

}

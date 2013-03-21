package dao;

import java.util.List;

import model.Admission;

public interface AdmissionDao {
    public void add(Admission admission) throws Exception;

    public void add(List<Admission> admissions) throws Exception;

    public Admission get(Integer userId) throws Exception;

    public void update(Admission admission) throws Exception;

    public void deleteByUids(List<Integer> uids) throws Exception;
    
    public Admission get(String idCardNo, String admissionCode) throws Exception;
}

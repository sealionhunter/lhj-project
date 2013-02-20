package service;

import java.util.List;

import org.springframework.validation.BindException;

import model.Apply;
import model.City;
import model.Depart;
import model.Exam;
import model.Master;
import model.Office;
import model.User;

import command.ModifyPasswordCommand;
import command.RegistCommand;

public interface RegistService {

    public void add(RegistCommand user) throws Exception;
    
    public List<Master> getMasters(int category) throws Exception;
    
    public List<Depart> listDepart() throws Exception;
    
    public List<Office> listOffice() throws Exception;
    
    public List<City> listCity() throws Exception;
    
    public List<Exam> listExam() throws Exception;
    public Apply getApply(Integer userId, Integer officeId) throws Exception;
    public Office getOffice(Integer officeId) throws Exception;

    public List<User> findUser(String username) throws Exception;
    public List<User> findIdCardNo(String idCardNo) throws Exception;
    
    public List<Apply> getApply(Integer userId) throws Exception;

	public void modifyPassword(ModifyPasswordCommand cmd, BindException errors) throws Exception ;

//    public void update(User user) throws Exception;
}

package dao;

import java.util.List;

import model.Apply;
import model.ApplyPK;

import command.SignUpPersonSearchCommand;

public interface ApplyDao {

    public ApplyPK add(Apply user) throws Exception;

    public Apply get(Integer userId, Integer officeId) throws Exception;

    public List<Apply> find(Integer userId) throws Exception;

    public void delete(Apply apply);

    public List<Apply> list(SignUpPersonSearchCommand cmd) throws Exception;

    public List<Apply> findApplyInfo(Integer userId) throws Exception;

    public void update(Apply apply) throws Exception;
}
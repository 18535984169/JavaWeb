package qqzone.dao.impl;

import myssm.BaseDao.BaseDAO;
import qqzone.dao.UserBasicDAO;
import qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @create 2023- 05- 22- 16:07
 * @desc
 */
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        return super.load("select * from t_user_basic where loginId = ? and pwd = ?",loginId,pwd);
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "select fid as id from t_friend where uid= ?";
        return super.executeQuery(sql,userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return load("select * from t_user_basic where id= ?",id);
    }
}

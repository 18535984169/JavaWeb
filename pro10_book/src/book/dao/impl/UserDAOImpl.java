package book.dao.impl;

import book.dao.UserDAO;
import book.pojo.User;
import myssm.BaseDao.BaseDAO;

/**
 * @create 2023- 05- 25- 19:39
 * @desc
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        return load("select * from t_user where uname like ? and pwd like ?",uname,pwd);
    }

    @Override
    public void addUser(User user) {
        super.executeUpdate("insert into t_user values(0,?,?,?,0)",user.getUname(),user.getPwd(),user.getEmail());
    }

    @Override
    public User getUser(String uname) {
        return load("select * from t_user where uname=?",uname);
    }
}

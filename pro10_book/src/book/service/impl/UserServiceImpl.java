package book.service.impl;

import book.dao.UserDAO;
import book.pojo.User;
import book.service.UserService;

/**
 * @create 2023- 05- 25- 19:41
 * @desc
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);
    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }

    @Override
    public User getUser(String uname) {
        return userDAO.getUser(uname);
    }
}

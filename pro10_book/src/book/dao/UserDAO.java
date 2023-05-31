package book.dao;

import book.pojo.User;

/**
 * @create 2023- 05- 25- 19:38
 * @desc
 */
public interface UserDAO {
    User getUser(String uname,String pwd);

    void addUser(User user);

    User getUser(String uname);
}

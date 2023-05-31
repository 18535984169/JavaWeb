package book.service;

import book.pojo.User;

/**
 * @create 2023- 05- 25- 19:41
 * @desc
 */
public interface UserService {
    User login(String uname,String pwd);

    void regist(User user);

    User getUser(String uname);
}

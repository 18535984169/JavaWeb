package qqzone.dao;

import qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @create 2023- 05- 22- 12:23
 * @desc
 */
public interface UserBasicDAO {
    //根据账号密码获取用户特定信息
    public UserBasic getUserBasic(String loginId,String pwd);
    //获取指定用户的所有好友列表
    public List<UserBasic> getUserBasicList(UserBasic userBasic);
    //根据ID查询userBasic的信息
     UserBasic getUserBasicById(Integer id );
}

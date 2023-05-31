package qqzone.service;

import qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @create 2023- 05- 22- 16:39
 * @desc
 */
public interface UserBasicService {
    UserBasic login (String loginId,String pwd);
    List<UserBasic> getfriedlist(UserBasic userBasic);
    //根据就ID获取指定用户信息
    UserBasic getUserBasicById(Integer id);
}

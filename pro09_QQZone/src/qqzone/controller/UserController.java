package qqzone.controller;

import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;
import qqzone.service.TopicService;
import qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @create 2023- 05- 22- 17:03
 * @desc
 */
public class UserController {
    private UserBasicService userBasicService;
    private TopicService topicService;
    public String login (String loginId,String pwd,HttpSession session){
        //1.登陆验证
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if (userBasic!=null){
            //不等于空 得到好友列表和日志列表
            List<UserBasic> friendList = userBasicService.getfriedlist(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);
            //放进去
            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);
            //传入会话作用域
            //userBasic 这个Key保存登陆着的信息
            //friend 保存当前进入的是谁的空间
            session.setAttribute("userBasic",userBasic);
            session.setAttribute("friend",userBasic);
            return "index";
        }else {
            return "login";
        }
    }

    public String friend(Integer id,HttpSession session){
        //1.根据ID获取指定的用户信息
        UserBasic currFriend = userBasicService.getUserBasicById(id);
        //根据用户信息获取日志
        List<Topic> topicList = topicService.getTopicList(currFriend);
        //把日志赋值给用户
        currFriend.setTopicList(topicList);

        session.setAttribute("friend",currFriend);

        return "index";
    }
}

package qqzone.controller;

import qqzone.pojo.Reply;
import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;
import qqzone.service.ReplyService;
import qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @create 2023- 05- 23- 10:42
 * @desc
 */
public class TopicController {
  private TopicService topicService;

    public  String topicDetail(Integer id, HttpSession session){
        Topic topic = topicService.getTopicById(id);
        session.setAttribute("topic",topic);
        return "frames/detail";
     }

     public String delTopic(Integer topicId){
        topicService.delTopic(topicId);
        return "redirect:topic.do?operate=getTopicList";
     }

     public String getTopicList(HttpSession session){
        //从session中获取当前用户信息，
         UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
         //再次获取当前用户关联的日志
         List<Topic> topicList = topicService.getTopicList(userBasic);
         //设置关联的日志列表 （因为之前的session中关联的friend的topicList和此刻数据库中的不一致）
         userBasic.setTopicList(topicList);
         //重新覆盖一下session中的信息 （因为main页面中迭代的是friend的日志）
         session.setAttribute("friend",userBasic);
         return "frames/main";
     }
}

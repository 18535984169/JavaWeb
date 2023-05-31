package qqzone.controller;

import qqzone.pojo.Reply;
import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;
import qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @create 2023- 05- 23- 15:38
 * @desc
 */
public class ReplyController {
    private ReplyService replyService;
    public String addReply(String content, Integer topicId,HttpSession session){
        UserBasic author = (UserBasic)session.getAttribute("userBasic");
        Reply reply = new Reply(content,LocalDateTime.now(),author,new Topic(topicId));
        replyService.addReply(reply);

        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }
    public String delReply(Integer replyId,Integer topicId){
       replyService.delReply(replyId);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }
}

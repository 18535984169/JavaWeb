package qqzone.service;

import qqzone.pojo.Reply;
import qqzone.pojo.Topic;

import java.util.List;

/**
 * @create 2023- 05- 23- 10:57
 * @desc
 */
public interface ReplyService {
    //根据topic的id获取关联的所有回复
    List<Reply> getReplyListByTopicId(Integer id);
    //添加回复
    void addReply(Reply reply);
    //删除指定的回复
    void delReply(Integer id);
    //删除指定的日志关联的所有回复
    void delReplyList(Topic topic);
}

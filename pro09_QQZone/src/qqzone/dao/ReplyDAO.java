package qqzone.dao;

import qqzone.pojo.Reply;
import qqzone.pojo.Topic;
import qqzone.service.ReplyService;

import java.util.List;

/**
 * @create 2023- 05- 22- 16:03
 * @desc
 */
public interface ReplyDAO {
    //获取指定日志的回复列表
    public List<Reply> getReplyList (Topic topic);
    //添加回复
    public void addReply(Reply reply);
    //删除回复
    public void delReply(Integer id);
    //根据ID获取回复
     Reply getReply(Integer id);
}

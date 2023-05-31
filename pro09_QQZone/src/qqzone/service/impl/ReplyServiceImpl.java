package qqzone.service.impl;

import qqzone.dao.ReplyDAO;
import qqzone.dao.UserBasicDAO;
import qqzone.pojo.HostReply;
import qqzone.pojo.Reply;
import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;
import qqzone.service.HostReplyService;
import qqzone.service.ReplyService;
import qqzone.service.UserBasicService;

import java.util.List;

/**
 * @create 2023- 05- 23- 11:00
 * @desc
 */
public class  ReplyServiceImpl implements ReplyService {
    private ReplyDAO replyDAO;


    //此处引用的是其他POJO的service接口，而不是DAO接口
    //以为其他POJO对应的业务逻辑是封装在service层的，我需要调用别人的业务逻辑方法，而不要去深入考虑别人内部的细节
    private HostReplyService hostReplyService;

    private UserBasicService userBasicService;

    //根据日志id获取相关的回复
    @Override
    public List<Reply> getReplyListByTopicId(Integer id) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(id));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);
            //1.将关联的作者设置进去
            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(author);
            //2.将关联的HostReply设置进去
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer id) {
        //1.根据ID获取到reply
        Reply reply = replyDAO.getReply(id);
        if (reply!=null) {
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            //2.如果有关联的HostReply，则先删除主人回复
            if (hostReply!=null){
                hostReplyService.delHostReply(hostReply.getId());
            }
        }
        //3.删除Reply
        replyDAO.delReply(id);
    }

    @Override
    public void delReplyList(Topic topic) {
        List<Reply> replyList = replyDAO.getReplyList(topic);
        if (replyList!=null){
            for (Reply reply : replyList){
                delReply(reply.getId());
            }
        }
    }
}

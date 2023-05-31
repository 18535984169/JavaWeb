package qqzone.service.impl;

import qqzone.dao.TopicDAO;
import qqzone.pojo.Reply;
import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;
import qqzone.pojo.UserDetail;
import qqzone.service.ReplyService;
import qqzone.service.TopicService;
import qqzone.service.UserBasicService;

import java.util.List;

/**
 * @create 2023- 05- 22- 16:56
 * @desc
 */
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null;
    private ReplyService replyService;

    private UserBasicService userBasicService;
    @Override
    public List<Topic> getTopicList(UserBasic userBasic)  {
        return topicDAO.getTopicList(userBasic);
    }

    //根据ID获取指定的topic信息，包含这个Topic关联的作者信息
    public Topic getTopic (Integer id){
        Topic topic = topicDAO.getTopic(id);
        UserBasic author = topic.getAuthor();
        author=userBasicService.getUserBasicById(author.getId());
        topic.setAuthor(author);
        return topic;
    }

    @Override
    public void delTopic(Integer topicId) {
        Topic topic = topicDAO.getTopic(topicId);
        if(topic!=null){
           replyService.delReplyList(topic);
           topicDAO.delTopic(topic);
        }
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = getTopic(id);
        List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
        topic.setReplyList(replyList);
        return  topic;
    }
}

package qqzone.service;

import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @create 2023- 05- 22- 16:55
 * @desc
 */
public interface TopicService {
    //根据用户查询特定用户的日志
    List<Topic> getTopicList(UserBasic userBasic);
    //根据ID获取特定topic
    Topic getTopicById (Integer id);

    //根据ID获取指定的topic信息，包含这个Topic关联的作者信息
    public Topic getTopic (Integer id);

    //删除指定ID的日志
    public void delTopic(Integer topicId);
}

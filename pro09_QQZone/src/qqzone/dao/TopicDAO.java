package qqzone.dao;

import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @create 2023- 05- 22- 16:00
 * @desc
 */
public interface TopicDAO {
    //获取指定用户的所有日志
    public List<Topic> getTopicList (UserBasic userBasic);
    //添加日志
    public void  addTopic (Topic topic);
    //删除日志
    public void delTopic(Topic topic);
    //获取特定日志信息
    public Topic getTopic(Integer id);
}

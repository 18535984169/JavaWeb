package qqzone.dao.impl;

import myssm.BaseDao.BaseDAO;
import qqzone.dao.TopicDAO;
import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @create 2023- 05- 22- 16:30
 * @desc
 */
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ?",userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {
        super.executeUpdate("delete  from t_topic where id= ?",topic.getId());
    }

    @Override
    public Topic getTopic(Integer id) {
        return load("select * from t_topic where id = ? " ,id);
    }
}

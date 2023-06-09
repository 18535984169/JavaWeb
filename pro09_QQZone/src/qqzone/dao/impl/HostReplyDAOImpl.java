package qqzone.dao.impl;

import myssm.BaseDao.BaseDAO;
import qqzone.dao.HostReplyDAO;
import qqzone.pojo.HostReply;

/**
 * @create 2023- 05- 23- 11:11
 * @desc
 */
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return load("select * from t_host_reply where reply = ?",replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        super.executeUpdate("delete from  t_host_reply where id =?",id);
    }
}

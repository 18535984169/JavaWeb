package qqzone.dao;

import qqzone.pojo.HostReply;

/**
 * @create 2023- 05- 22- 16:07
 * @desc
 */
public interface HostReplyDAO {
    //根据replyId获取相关的主人回复实体
    HostReply getHostReplyByReplyId(Integer replyId);

    void delHostReply(Integer id);
}

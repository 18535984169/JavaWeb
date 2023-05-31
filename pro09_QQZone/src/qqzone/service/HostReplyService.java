package qqzone.service;

import qqzone.pojo.HostReply;

/**
 * @create 2023- 05- 23- 11:08
 * @desc
 */
public interface HostReplyService {
    HostReply getHostReplyByReplyId (Integer replyId);
    //删除主人回复
    void delHostReply(Integer id);
}

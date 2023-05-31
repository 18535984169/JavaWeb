package qqzone.service.impl;

import qqzone.dao.HostReplyDAO;
import qqzone.pojo.HostReply;
import qqzone.service.HostReplyService;

/**
 * @create 2023- 05- 23- 11:09
 * @desc
 */
public class HostReplyServiceImpl implements HostReplyService {
    private HostReplyDAO hostReplyDAO;
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        hostReplyDAO.delHostReply(id);
    }
}

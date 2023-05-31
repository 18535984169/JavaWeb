package qqzone.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @create 2023- 05- 21- 19:47
 * @desc
 */
public class Topic {
    private Integer id;
    private String  title;
    private String content;
    private LocalDateTime topicDate;
    private UserBasic author;  //m:1

    private List<Reply> replyList; //1:n  日志的多个回复

    public Topic() {
    }

    public Topic(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTopicDate() {
        return topicDate;
    }

    public void setTopicDate(LocalDateTime topicDate) {
        this.topicDate = topicDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}

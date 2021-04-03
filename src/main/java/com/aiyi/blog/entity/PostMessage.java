package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.ID;
import com.aiyi.core.annotation.po.TableName;

/**
 * 帖子相关消息通知表
 */
@TableName(name = "bbs_post_message")
public class PostMessage {

    @ID
    private long id;

    /**
     * 帖子ID
     */
    private long postId;

    /**
     * 帖子作者用户ID
     */
    private int autherUserId;

    /**
     * 消息来源用户ID
     */
    private int userId;

    /**
     * 消息来源用户昵称
     */
    private String userNicker;

    /**
     * 0 = 艾特， 1 = 评论， 2 = 点赞
     */
    private int type;

    /**
     * 作者是否已读
     */
    private boolean read;

    /**
     * 评论内容
     */
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public int getAutherUserId() {
        return autherUserId;
    }

    public void setAutherUserId(int autherUserId) {
        this.autherUserId = autherUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserNicker() {
        return userNicker;
    }

    public void setUserNicker(String userNicker) {
        this.userNicker = userNicker;
    }
}

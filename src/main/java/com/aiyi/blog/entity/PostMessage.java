package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.FieldName;
import com.aiyi.core.annotation.po.ID;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.beans.PO;

import java.util.Date;

/**
 * 帖子相关消息通知表
 */
@TableName(name = "bbs_post_message")
public class PostMessage extends PO {

    @ID
    private long id;

    /**
     * 帖子ID
     */
    @FieldName(name = "post_id")
    private long postId;

    /**
     * 评论ID
     */
    @FieldName(name = "comment_id")
    private long commentId;

    /**
     * 帖子作者用户ID
     */
    @FieldName(name = "author_user_id")
    private int authorUserId;

    /**
     * 消息来源用户ID
     */
    @FieldName(name = "user_id")
    private int userId;

    /**
     * 消息来源用户昵称
     */
    @FieldName(name = "user_nicker")
    private String userNicker;

    /**
     * 消息来源用户头像
     */
    @FieldName(name = "user_header_img")
    private String userHeaderImg;

    /**
     * 0 = 艾特， 1 = 评论， 2 = 点赞
     */
    private int type;

    /**
     * 作者是否已读
     */
    @FieldName(name = "`read`")
    private boolean hasRead;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    @FieldName(name = "create_time")
    private Date createTime = new Date();

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

    public int getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(int authorUserId) {
        this.authorUserId = authorUserId;
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

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserHeaderImg() {
        return userHeaderImg;
    }

    public void setUserHeaderImg(String userHeaderImg) {
        this.userHeaderImg = userHeaderImg;
    }
}

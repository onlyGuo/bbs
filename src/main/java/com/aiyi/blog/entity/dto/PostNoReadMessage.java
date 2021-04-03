package com.aiyi.blog.entity.dto;

/**
 * 帖子相关未读消息封装
 */
public class PostNoReadMessage {

    /**
     * 艾特我的未读消息数量
     */
    private int at;

    /**
     * 评论我的未读消息数量
     */
    private int comment;

    /**
     * 点赞我的未读消息数量
     */
    private int love;

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }
}

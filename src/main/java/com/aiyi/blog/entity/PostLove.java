package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.FieldName;
import com.aiyi.core.annotation.po.ID;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.beans.PO;

/**
 * 帖子点赞列表
 */
@TableName(name = "bbs_post_love")
public class PostLove extends PO {

    @ID
    private long id;

    @FieldName(name = "post_id")
    private long postId;

    @FieldName(name = "user_id")
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

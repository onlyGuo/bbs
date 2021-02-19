package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.FieldName;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.beans.PO;

import java.util.Date;

/**
 * 访问记录
 */
@TableName(name = "blog_log")
public class Log extends PO {

    private long id;

    @FieldName(name = "create_time")
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

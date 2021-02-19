package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.FieldName;
import com.aiyi.core.annotation.po.ID;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.annotation.po.TempField;
import com.aiyi.core.beans.PO;

import java.util.Date;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/15 11:48
 * @Email 719348277@qq.com
 * @Description: 栏目
 */
@TableName(name = "blog_classify")
public class Classify extends PO {

    @ID
    private int id;

    private String name;

    /**
     * 1 = 正常, 0 = 不可见
     */
    private int status;

    @FieldName(name = "create_time")
    private Date createTime;

    @FieldName(name = "update_time")
    private Date updateTime;

    @TempField
    private boolean selected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
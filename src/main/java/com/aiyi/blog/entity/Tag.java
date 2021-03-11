package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.beans.PO;

/**
 * 标签信息
 * @author: gsk
 * @email: 719348277@qq.com
 * @date: 2021/3/9 4:55 下午
 */
@TableName(name = "bbs_tag")
public class Tag extends PO {

    private int id;

    private String name;

    private boolean anonymous;

    private int rule;

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

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }
}

package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.FieldName;
import com.aiyi.core.annotation.po.ID;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.annotation.po.TempField;
import com.aiyi.core.annotation.po.vali.Validation;
import com.aiyi.core.beans.PO;

import java.util.Date;
import java.util.List;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/16 15:29
 * @Email 719348277@qq.com
 * @Description: 文章
 */
@TableName(name = "blog_article")
public class Article extends PO {

    @ID
    private int id;

    @Validation(name = "文章标题", maxLength = 150)
    private String title;

    @Validation(name = "文章源码")
    private String doc;

    private String outline;

    @Validation(name = "文章内容")
    private String content;

    @FieldName(name = "create_time")
    private Date createTime;

    @FieldName(name = "update_time")
    private Date updateTime;

    @FieldName(name = "display_count")
    private long displayCount;

    private boolean deleted;

    @FieldName(name = "cover_image")
    private String coverImage;

    @TempField
    private List<Integer> classifyIds;

    @TempField
    private String classifyName;

    @TempField
    private List<Classify> classifyList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Integer> getClassifyIds() {
        return classifyIds;
    }

    public void setClassifyIds(List<Integer> classifyIds) {
        this.classifyIds = classifyIds;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Classify> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<Classify> classifyList) {
        this.classifyList = classifyList;
    }

    public long getDisplayCount() {
        return displayCount;
    }

    public void setDisplayCount(long displayCount) {
        this.displayCount = displayCount;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
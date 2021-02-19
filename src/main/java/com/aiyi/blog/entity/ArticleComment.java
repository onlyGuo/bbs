package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.FieldName;
import com.aiyi.core.annotation.po.ID;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.annotation.po.TempField;
import com.aiyi.core.annotation.po.vali.Validation;
import com.aiyi.core.annotation.po.vali.enums.ValidationType;
import com.aiyi.core.beans.PO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/27 16:50
 * @Email 719348277@qq.com
 * @Description: 评论实体
 */
@TableName(name = "blog_article_comment")
public class ArticleComment extends PO {

    @ID
    private int id;

    @FieldName(name = "article_id")
    private int articleId;

    @FieldName(name = "parent_id")
    private int parentId;

    @TempField
    private String replyName;

    @Validation(name = "昵称")
    private String nicker;

    @Validation(value = ValidationType.Email, name = "邮箱")
    private String email;

    @Validation(name = "评论内容")
    private String content;

    @FieldName(name = "create_time")
    private Date createTime;

    private int status;

    private boolean deleted;

    @TempField
    List<ArticleComment> subList;

    @TempField
    private int valicode;

    @TempField
    private String valiIndex;

    @TempField
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getNicker() {
        return nicker;
    }

    public void setNicker(String nicker) {
        this.nicker = nicker;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ArticleComment> getSubList() {
        if (null == subList){
            subList = new LinkedList<>();
        }
        return subList;
    }

    public void setSubList(List<ArticleComment> subList) {
        this.subList = subList;
    }

    public int getValicode() {
        return valicode;
    }

    public void setValicode(int valicode) {
        this.valicode = valicode;
    }

    public String getValiIndex() {
        return valiIndex;
    }

    public void setValiIndex(String valiIndex) {
        this.valiIndex = valiIndex;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.FieldName;
import com.aiyi.core.annotation.po.ID;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.beans.PO;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/16 15:32
 * @Email 719348277@qq.com
 * @Description: 文章与栏目关联
 */
@TableName(name = "blog_article_classify")
public class ArticleClassify extends PO {

    @ID
    private int id;

    @FieldName(name = "article_id")
    private int articleId;

    @FieldName(name = "classify_id")
    private int classifyId;

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

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }
}
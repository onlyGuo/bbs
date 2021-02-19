package com.aiyi.blog.service;

import com.aiyi.blog.entity.ArticleComment;
import com.aiyi.core.beans.ResultPage;

import java.util.List;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/27 17:03
 * @Email 719348277@qq.com
 * @Description: 文章评论相关业务
 */
public interface ArticleCommentService {

    /**
     * 列出某个文章的评论列表
     * @param articleId
     *      文章ID
     * @return
     */
    List<ArticleComment> list(int articleId);

    /**
     * 发布评论
     * @param articleComment
     *      评论内容
     */
    void publish(ArticleComment articleComment);

    /**
     * 列出评论列表
     * @param keyword
     *      关键字
     * @param deleted
     *      是否已删除
     * @param page
     *      页码
     * @param limit
     *      每页条数
     * @return
     */
    ResultPage<ArticleComment> list(String keyword, Integer deleted, int page, int limit);

    /**
     * 删除评论
     * @param id
     *      评论ID
     */
    void del(int id);

    /**
     * 修改评论状态
     * @param comment
     *      评论状态
     */
    void updateStatus(ArticleComment comment);
}

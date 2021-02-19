package com.aiyi.blog.service;

import com.aiyi.blog.entity.Article;
import com.aiyi.core.beans.ResultPage;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/16 16:58
 * @Email 719348277@qq.com
 * @Description: 文章相关业务
 */
public interface ArticleService {

    /**
     * 列出文章列表
     * @param title
     *      标题过滤
     * @param classifyId
     *      关联栏目ID
     * @param page
     *      页码
     * @param pageSize
     *      每页条数
     * @return
     */
    ResultPage<Article> list(String title, Integer classifyId, int page, int pageSize);

    /**
     * 创建新文章
     * @param article
     *      文章信息
     */
    Article create(Article article);

    /**
     * 获得文章信息
     * @param id
     *      文章ID
     * @return
     */
    Article get(int id);

    /**
     * 修改文章信息
     * @param article
     *      文章信息
     */
    void update(Article article);

    /**
     * 近一年文章数量
     * @return
     */
    long yearCount();
}

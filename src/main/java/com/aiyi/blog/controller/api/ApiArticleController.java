package com.aiyi.blog.controller.api;

import com.aiyi.blog.assets.NoLogin;
import com.aiyi.blog.conf.CommonAttr;
import com.aiyi.blog.entity.Article;
import com.aiyi.blog.entity.ArticleComment;
import com.aiyi.blog.entity.dto.LayerTable;
import com.aiyi.blog.service.ArticleCommentService;
import com.aiyi.blog.service.ArticleService;
import com.aiyi.blog.util.cache.CacheUtil;
import com.aiyi.blog.util.cache.Key;
import com.aiyi.core.beans.ResultBean;
import com.aiyi.core.exception.ValidationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/16 15:29
 * @Email 719348277@qq.com
 * @Description: 文章相关接口
 */
@RestController
@RequestMapping("api/v1/article")
public class ApiArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleCommentService articleCommentService;

    /**
     * 列出文章列表
     * @param title
     *      标题过滤
     * @param classifyId
     *      关联栏目ID
     * @param page
     *      页码
     * @param limit
     *      每页条数
     * @return
     */
    @GetMapping
    public LayerTable<Article> list(String title, Integer classifyId, int page, int limit){
        if (classifyId != null && 0 == classifyId){
            classifyId = null;
        }
        return LayerTable.createByPageResult(articleService.list(title, classifyId, page, limit));
    }

    /**
     * 编辑文章
     * @param article
     *      文章信息
     * @return
     */
    @PutMapping
    public ResultBean putArticle(@RequestBody Article article){
        if (article.getId() == 0){
            article = articleService.create(article);
        }else{
            articleService.update(article);
        }
        return ResultBean.success().setResponseBody(article);
    }

    /**
     * 发布评论
     * @param articleId
     *      文章ID
     * @param comment
     *      评论内容
     * @return
     */
    @PostMapping("{articleId}/comment")
    @NoLogin
    public ResultBean publishComment(@PathVariable int articleId, @RequestBody ArticleComment comment){
        comment.setArticleId(articleId);
        Integer integer = CacheUtil.get(Key.as(CommonAttr.CACHE.VALIDATION_CODE, comment.getValiIndex()), Integer.class);
        if (null == integer || comment.getValicode() != integer){
            throw new ValidationException("验证码有误");
        }
        articleCommentService.publish(comment);
        return ResultBean.success();
    }

}
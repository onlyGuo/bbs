package com.aiyi.blog.controller;

import com.aiyi.blog.assets.NoLogin;
import com.aiyi.blog.entity.Article;
import com.aiyi.blog.entity.Classify;
import com.aiyi.blog.service.ArticleCommentService;
import com.aiyi.blog.service.ArticleService;
import com.aiyi.blog.service.ClassifyService;
import com.aiyi.blog.service.record.RecordService;
import com.aiyi.core.beans.ResultPage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 文章
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleCommentService articleCommentService;

    @Resource
    private ClassifyService classifyService;

    @Resource
    private RecordService recordService;


    /**
     * 文章详情页面
     * @param articleId
     *      文章ID
     * @return
     */
    @NoLogin
    @RequestMapping("{articleId}")
    public String article(@PathVariable int articleId, Model model){
        Article article = articleService.get(articleId);
        if (null == article){
            return "404";
        }

        // 记录相关信息
        recordService.recordArticle(articleId);

        model.addAttribute("article", article);
        model.addAttribute("validationIndex", UUID.randomUUID().toString().replace("-", ""));
        model.addAttribute("comments", articleCommentService.list(articleId));
        ResultPage<Classify> classifyList = classifyService.list(null, 1, 1, Integer.MAX_VALUE);
        model.addAttribute("classifyList", classifyList.getList());
        return "article";
    }

}

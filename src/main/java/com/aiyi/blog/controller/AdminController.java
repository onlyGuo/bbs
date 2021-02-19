package com.aiyi.blog.controller;

import com.aiyi.blog.assets.NoLogin;
import com.aiyi.blog.dao.ArticleCommentDao;
import com.aiyi.blog.entity.Article;
import com.aiyi.blog.entity.ArticleComment;
import com.aiyi.blog.service.ArticleService;
import com.aiyi.blog.service.ClassifyService;
import com.aiyi.blog.service.StatisticsService;
import com.aiyi.blog.service.WebSiteService;
import com.aiyi.core.beans.Method;
import com.aiyi.core.sql.where.C;
import com.aiyi.core.util.DateUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.Date;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Resource
    private WebSiteService webSiteService;

    @Resource
    private ClassifyService classifyService;

    @Resource
    private ArticleService articleService;

    @Resource
    private StatisticsService statisticsService;

    @Resource
    private ArticleCommentDao articleCommentDao;


    @RequestMapping
    public String index(){
        return "admin/index";
    }

    /**
     * 控制台
     * @return
     */
    @RequestMapping("console")
    public String console(Model model){
        model.addAttribute("weekVisit", statisticsService.weekVisit());
        model.addAttribute("allVisit", statisticsService.allVisit());
        // 近30天评论
        long commentCount = articleCommentDao.count(Method
                .where(ArticleComment::getCreateTime, C.DE, new Date(System.currentTimeMillis() - DateUtil.DAY_TIME * 30)));
        model.addAttribute("commentCount", commentCount);
        // 新评占比
        long allCount = articleCommentDao.count(Method.createDefault());
        if (0 == allCount){
            model.addAttribute("newComment", 0);
        }else{
            model.addAttribute("newComment", commentCount / allCount * 100);
        }
        // 年文章
        model.addAttribute("yearArticleCount", articleService.yearCount());
        return "admin/console";
    }

    /**
     * 系统设置
     * @return
     */
    @RequestMapping("system")
    public String system(Model model){
        model.addAttribute("website", webSiteService.getWebSite());
        return "admin/system";
    }

    /**
     * 栏目管理
     * @return
     */
    @RequestMapping("classify")
    public String classify(){
        return "admin/classify";
    }

    /**
     * 用户管理
     * @return
     */
    @RequestMapping("classify-from")
    public String classifyFrom(){
        return "admin/classify-from";
    }

    /**
     * 文章管理
     * @return
     */
    @RequestMapping("article")
    public String article(Model model){
        model.addAttribute("classifys",
                classifyService.list(null, 1, 1, Integer.MAX_VALUE).getList());
        return "admin/article";
    }

    /**
     * 文章发布页
     * @return
     */
    @RequestMapping("article-from")
    public String articleFrom(Model model){
        model.addAttribute("classifys",
                classifyService.list(null, 1, 1, Integer.MAX_VALUE).getList());
        return "admin/article-from";
    }

    /**
     * 文章编辑页
     * @return
     */
    @RequestMapping("article-from/{id}")
    public String articleFrom(Model model, @PathVariable int id){
        Article article = articleService.get(id);
        model.addAttribute("article", article);
        model.addAttribute("classifys", article.getClassifyList());
        return "admin/article-from";
    }

    /**
     * 评论管理页
     * @return
     */
    @RequestMapping("comment")
    public String comment(Model model){
        return "admin/comment";
    }

    /**
     * 规则过滤
     * @param model
     * @return
     */
    @RequestMapping("rule")
    public String rule(Model model){
        return "admin/rule";
    }

    /**
     * 规则编辑
     * @param model
     * @return
     */
    @RequestMapping("rule-from")
    public String ruleFrom(Model model){
        return "admin/rule-from";
    }

}

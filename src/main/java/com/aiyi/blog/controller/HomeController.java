package com.aiyi.blog.controller;

import com.aiyi.blog.assets.NoLogin;
import com.aiyi.blog.entity.Article;
import com.aiyi.blog.entity.Classify;
import com.aiyi.blog.entity.dto.ELResultPage;
import com.aiyi.blog.service.ArticleService;
import com.aiyi.blog.service.ClassifyService;
import com.aiyi.core.beans.ResultPage;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/20 15:39
 * @Email 719348277@qq.com
 * @Description: 主页
 */
@Controller
@RequestMapping
public class HomeController {

    @Resource
    private ArticleService articleService;

    @Resource
    private ClassifyService classifyService;

    /**
     * 主页
     * @return
     */
    @RequestMapping
    @NoLogin
    public String index(Model model, HttpServletRequest request){
        ResultPage<Article> list = articleService.list(null, null, 1, 10);
        model.addAttribute("articleList", list);
        model.addAttribute("page", ELResultPage.createByResultPage(list));
        ResultPage<Classify> classifyList = classifyService.list(null, 1, 1, Integer.MAX_VALUE);
        model.addAttribute("classifyList", classifyList.getList());
        request.getSession().removeAttribute("classifyId");
        return "index";
    }

    /**
     * 主页分页
     * @param page
     *      页码
     * @param size
     *      每页条数
     * @param model
     * @return
     */
    @RequestMapping("{page}-{size}")
    @NoLogin
    public String indexPage(@PathVariable int page, @PathVariable int size, Model model, HttpServletRequest request){
        ResultPage<Article> list = articleService.list(null, null, page, size);
        model.addAttribute("articleList", list);
        model.addAttribute("page", ELResultPage.createByResultPage(list));
        ResultPage<Classify> classifyList = classifyService.list(null, 1, 1, Integer.MAX_VALUE);
        model.addAttribute("classifyList", classifyList.getList());
        request.getSession().removeAttribute("classifyId");
        return "index";
    }

    /**
     * 分类列表
     * @param model
     * @param classifyId
     *      分类ID
     * @return
     */
    @RequestMapping("classify/{classifyId}")
    @NoLogin
    public String classifyIndex(Model model, @PathVariable int classifyId, HttpServletRequest request){
        ResultPage<Article> list = articleService.list(null, classifyId, 1, 10);
        model.addAttribute("articleList", list);
        model.addAttribute("page", ELResultPage.createByResultPage(list));
        ResultPage<Classify> classifyList = classifyService.list(null, 1, 1, Integer.MAX_VALUE);
        model.addAttribute("classifyList", classifyList.getList());
        model.addAttribute("classifyLocation", "classify/" + classifyId);
        request.getSession().setAttribute("classifyId", classifyId);
        for (Classify classify: classifyList.getList()){
            if (classify.getId() == classifyId){
                model.addAttribute("classify", classify);
            }
        }
        return "classify";
    }

    /**
     * 分类文章分页列表
     * @param model
     * @param classifyId
     *      分类ID
     * @param page
     *      页码
     * @param pageSize
     *      每页条数
     * @return
     */
    @RequestMapping("classify/{classifyId}/{page}-{pageSize}")
    @NoLogin
    public String classifyIndex(Model model, @PathVariable int classifyId, @PathVariable int page,
                                @PathVariable int pageSize, HttpServletRequest request){
        ResultPage<Article> list = articleService.list(null, classifyId, page, pageSize);
        model.addAttribute("articleList", list);
        model.addAttribute("page", ELResultPage.createByResultPage(list));
        ResultPage<Classify> classifyList = classifyService.list(null, 1, 1, Integer.MAX_VALUE);
        model.addAttribute("classifyList", classifyList.getList());
        model.addAttribute("classifyLocation", "classify/" + classifyId);
        request.getSession().setAttribute("classifyId", classifyId);
        for (Classify classify: classifyList.getList()){
            if (classify.getId() == classifyId){
                model.addAttribute("classify", classify);
            }
        }
        return "classify";
    }

}
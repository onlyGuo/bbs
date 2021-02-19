package com.aiyi.blog.service.impl;

import com.aiyi.blog.dao.ArticleClassifyDao;
import com.aiyi.blog.dao.ArticleDao;
import com.aiyi.blog.dao.ArticleMapper;
import com.aiyi.blog.entity.Article;
import com.aiyi.blog.entity.ArticleClassify;
import com.aiyi.blog.entity.Classify;
import com.aiyi.blog.service.ArticleService;
import com.aiyi.blog.service.ClassifyService;
import com.aiyi.blog.util.StringHtmlUtil;
import com.aiyi.core.beans.LeftJoin;
import com.aiyi.core.beans.Method;
import com.aiyi.core.beans.ResultPage;
import com.aiyi.core.beans.WherePrams;
import com.aiyi.core.exception.ValidationException;
import com.aiyi.core.sql.where.C;
import com.aiyi.core.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/16 17:21
 * @Email 719348277@qq.com
 * @Description: 文章相关业务实现
 */
@Service
public class ArticleSerivceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleClassifyDao articleClassifyDao;

    @Resource
    private ClassifyService classifyService;

    @Override
    public ResultPage<Article> list(String title, Integer classifyId, int page, int pageSize) {
        return articleMapper.listByPage(title, classifyId, page, pageSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article create(Article article) {
        builderArticle(article);
        articleDao.add(article);

        // 添加关联
        for (Integer id: article.getClassifyIds()){
            ArticleClassify articleClassify = new ArticleClassify();
            articleClassify.setArticleId(article.getId());
            articleClassify.setClassifyId(id);
            articleClassifyDao.add(articleClassify);
        }
        return article;
    }

    @Override
    public Article get(int id) {
        Article article = articleDao.get(Method.where(Article::getId, C.EQ, id)
                .and(Article::isDeleted, C.EQ, false));
        if (null == article){
            return null;
        }
        List<ArticleClassify> list = articleClassifyDao.list(Method
                .where(ArticleClassify::getArticleId, C.EQ, article.getId()));
        List<Classify> classifyList = classifyService.list(null, 1, 1, Integer.MAX_VALUE).getList();

        HashSet<Integer> idSet = new HashSet<>();
        for (ArticleClassify articleClassify: list){
            idSet.add(articleClassify.getClassifyId());
        }
        for (Classify classify: classifyList){
            classify.setSelected(idSet.contains(classify.getId()));
        }
        article.setClassifyList(classifyList);
        return article;
    }

    @Override
    public void update(Article article) {
        builderArticle(article);
        Article dbArticle = articleDao.get(article.getId());
        if (null == dbArticle){
            create(article);
            return;
        }
        article.setCreateTime(dbArticle.getCreateTime());
        articleDao.update(article);

        // 添加关联
        articleClassifyDao.del(Method.where(ArticleClassify::getArticleId, C.EQ, article.getId()));
        for (Integer id: article.getClassifyIds()){
            ArticleClassify articleClassify = new ArticleClassify();
            articleClassify.setArticleId(article.getId());
            articleClassify.setClassifyId(id);
            articleClassifyDao.add(articleClassify);
        }
    }

    @Override
    public long yearCount() {
        return articleDao.count(Method.where(Article::getCreateTime, C.DE, new Date(System.currentTimeMillis() - DateUtil.DAY_TIME * 365)));
    }

    private void builderArticle(Article article){
        article.check("title", "doc", "content");
        if (null == article.getClassifyIds() || article.getClassifyIds().isEmpty()){
            throw new ValidationException("文章分类不能为空");
        }
        article.setOutline(StringHtmlUtil.outline(article.getContent()));
        article.setCreateTime(new Date());
        article.setUpdateTime(article.getCreateTime());
    }
}
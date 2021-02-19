package com.aiyi.blog.service.record;

import com.aiyi.blog.dao.ArticleDao;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 记录相关数据
 */
@Component
public class RecordService {

    @Resource
    private ArticleDao articleDao;

    @Async
    public void recordArticle(int articleId){
        articleDao.execute("UPDATE " + articleDao.tableName() + " SET display_count = display_count + 1 WHERE id = ?", articleId);
    }

}

package com.aiyi.blog.service;

import com.aiyi.blog.entity.Classify;
import com.aiyi.core.beans.ResultPage;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/15 11:51
 * @Email 719348277@qq.com
 * @Description: 栏目相关业务
 */
public interface ClassifyService {

    /**
     * 列出栏目列表
     * @param keyword
     *      关键词
     * @param status
     *      状态
     * @return
     */
    ResultPage<Classify> list(String keyword, int status, int page, int pageSize);

    /**
     * 天厨分类/栏目列表
     * @param classify
     *      栏目信息
     */
    void create(Classify classify);
}

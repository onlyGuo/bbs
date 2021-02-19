package com.aiyi.blog.service;

import com.aiyi.blog.entity.Rule;
import com.aiyi.core.beans.ResultPage;

import java.util.List;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/31 14:21
 * @Email 719348277@qq.com
 * @Description: 评论过滤规则相关业务实现
 */
public interface RuleService {

    /**
     * 列出所有启用的规则列表
     * @return
     */
    List<Rule> list();

    /**
     * 根据可选状态的规则列表
     * @param status
     *      规则状态
     * @return
     */
    ResultPage<Rule> list(Integer status, int page, int pageSize);

    /**
     * 创建一个评论过滤规则
     * @param rule
     */
    void create(Rule rule);

    /**
     * 修改一个评论过滤规则状态
     * @param rule
     *      规则信息
     */
    void updateStatus(Rule rule);
}

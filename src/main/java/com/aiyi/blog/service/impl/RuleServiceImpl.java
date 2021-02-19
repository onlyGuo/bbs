package com.aiyi.blog.service.impl;

import com.aiyi.blog.conf.CommonAttr;
import com.aiyi.blog.dao.RuleDao;
import com.aiyi.blog.entity.Rule;
import com.aiyi.blog.service.RuleService;
import com.aiyi.core.beans.Method;
import com.aiyi.core.beans.ResultPage;
import com.aiyi.core.beans.WherePrams;
import com.aiyi.core.sql.where.C;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/31 14:23
 * @Email 719348277@qq.com
 * @Description: 规则相关业务实现类
 */
@Service
public class RuleServiceImpl implements RuleService {

    @Resource
    private RuleDao ruleDao;


    @Override
    public List<Rule> list() {
        return ruleDao.list(Method.where(Rule::getStatus, C.EQ, CommonAttr.SWITCH.ON));
    }

    @Override
    public ResultPage<Rule> list(Integer status, int page, int pageSize) {
        WherePrams where = Method.createDefault();
        if (null != status && -1 != status){
            where = Method.where(Rule::getStatus, C.EQ, status);
        }
        return ruleDao.list(where, page, pageSize);
    }

    @Override
    public void create(Rule rule) {

    }

    @Override
    public void updateStatus(Rule rule) {

    }
}
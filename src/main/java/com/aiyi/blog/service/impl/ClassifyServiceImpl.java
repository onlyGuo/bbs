package com.aiyi.blog.service.impl;

import com.aiyi.blog.dao.ClassifyDao;
import com.aiyi.blog.entity.Classify;
import com.aiyi.blog.service.ClassifyService;
import com.aiyi.core.beans.Method;
import com.aiyi.core.beans.ResultPage;
import com.aiyi.core.beans.WherePrams;
import com.aiyi.core.exception.ValidationException;
import com.aiyi.core.sql.where.C;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/15 11:52
 * @Email 719348277@qq.com
 * @Description: 栏目相关业务实现
 */
@Service
public class ClassifyServiceImpl implements ClassifyService {

    @Resource
    private ClassifyDao classifyDao;

    @Override
    public ResultPage<Classify> list(String keyword, int status, int page, int pageSize) {
        WherePrams where = Method.createDefault();
        if (!StringUtils.isEmpty(keyword)){
            where.and(Classify::getName, C.LIKE, keyword);
        }
        if (status != -1){
            where.and(Classify::getStatus, C.EQ, status);
        }
        return classifyDao.list(where, page, pageSize);
    }

    @Override
    public void create(Classify classify) {
        classify.setCreateTime(new Date());
        classify.setUpdateTime(new Date());
        if (classifyDao.isExist(Method.where(Classify::getName, C.EQ, classify.getName()))){
            throw new ValidationException("该名称已存在");
        }
        classifyDao.add(classify);
    }
}
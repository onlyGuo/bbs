package com.aiyi.blog.service.impl;

import com.aiyi.blog.dao.LogDao;
import com.aiyi.blog.entity.Log;
import com.aiyi.blog.service.StatisticsService;
import com.aiyi.core.beans.Method;
import com.aiyi.core.sql.where.C;
import com.aiyi.core.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private LogDao logDao;

    @Override
    public long weekVisit() {
        return logDao.count(Method.where(Log::getCreateTime, C.DE, new Date(System.currentTimeMillis() - DateUtil.DAY_TIME * 7)));
    }

    @Override
    public long allVisit() {
        return logDao.count(Method.createDefault());
    }
}

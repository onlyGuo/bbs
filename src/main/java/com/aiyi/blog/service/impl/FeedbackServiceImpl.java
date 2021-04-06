package com.aiyi.blog.service.impl;

import com.aiyi.blog.dao.FeedbackDao;
import com.aiyi.blog.entity.Feedback;
import com.aiyi.blog.service.FeedbackService;
import com.aiyi.core.util.thread.ThreadUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 意见反馈相关业务实现类
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackDao feedbackDao;

    @Override
    public void postFeedback(Feedback feedback) {
        feedback.setCreateTime(new Date());
        feedback.setUserId(ThreadUtil.getUserId().intValue());
        feedbackDao.add(feedback);
    }
}

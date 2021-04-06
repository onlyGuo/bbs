package com.aiyi.blog.service;

import com.aiyi.blog.entity.Feedback;

public interface FeedbackService {

    /**
     * 提交反馈
     * @param feedback
     *      反馈信息
     */
    void postFeedback(Feedback feedback);

}

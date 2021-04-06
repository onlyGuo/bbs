package com.aiyi.blog.controller.api;

import com.aiyi.blog.entity.Feedback;
import com.aiyi.blog.service.FeedbackService;
import com.aiyi.core.beans.ResultBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 意见反馈相关接口
 */
@RestController
@RequestMapping("api/v1/feedback")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    /**
     * 提交反馈
     * @param feedback
     *      反馈信息
     * @return
     */
    @PostMapping
    public ResultBean postFeedback(@RequestBody Feedback feedback){
        feedbackService.postFeedback(feedback);
        return ResultBean.success();
    }

}

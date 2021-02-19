package com.aiyi.blog.controller.api;

import com.aiyi.blog.assets.NoLogin;
import com.aiyi.blog.conf.CommonAttr;
import com.aiyi.blog.util.CodeUtil;
import com.aiyi.blog.util.cache.CacheUtil;
import com.aiyi.blog.util.cache.Key;
import com.aiyi.core.beans.ResultBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/28 11:20
 * @Email 719348277@qq.com
 * @Description: 验证码相关接口
 */
@RestController
@RequestMapping("api/v1/vc")
public class ApiValidationCodeController {

    /**
     * 取验证码
     * @param index
     *      验证码索引
     * @return
     */
    @GetMapping("{index}")
    @NoLogin
    public ResultBean validation(@PathVariable String index){
        int rand = CodeUtil.rand(10, 99);
        CacheUtil.put(Key.as(CommonAttr.CACHE.VALIDATION_CODE, index), rand * 288, TimeUnit.MINUTES, 5);
        return ResultBean.success().setResponseBody(rand);
    }
}
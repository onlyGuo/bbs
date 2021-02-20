package com.aiyi.blog.controller.api;

import com.aiyi.blog.assets.NoLogin;
import com.aiyi.blog.entity.User;
import com.aiyi.blog.service.UserService;
import com.aiyi.blog.util.cache.CacheUtil;
import com.aiyi.core.beans.ResultBean;
import com.aiyi.core.util.thread.ThreadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/9 16:49
 * @Email 719348277@qq.com
 * @Description: 用户相关接口
 */
@RestController
@RequestMapping("api/v1/user")
public class ApiUserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     * @param user
     *      用户信息
     * @return
     */
    @PostMapping("login")
    @NoLogin
    public ResultBean login(@RequestBody User user){
        user.check("phone", "password");
        String token = userService.login(user);
        System.out.println(CacheUtil.dump());
        return ResultBean.success().putResponseBody("token", token);
    }

    /**
     * 用户注册
     * @param user
     *      用户信息
     * @return
     */
    @PostMapping("register")
    @NoLogin
    public ResultBean register(@RequestBody User user){
        user.check("phone", "password");
        userService.register(user);
        return ResultBean.success();
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("logout")
    public ResultBean logout(){
        userService.logout(ThreadUtil.getUserId().intValue());
        return ResultBean.success();
    }



}
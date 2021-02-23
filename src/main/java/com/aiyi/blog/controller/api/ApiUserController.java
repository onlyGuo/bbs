package com.aiyi.blog.controller.api;

import com.aiyi.blog.assets.NoLogin;
import com.aiyi.blog.entity.User;
import com.aiyi.blog.service.UserService;
import com.aiyi.blog.util.cache.CacheUtil;
import com.aiyi.core.beans.ResultBean;
import com.aiyi.core.exception.ValidationException;
import com.aiyi.core.util.MD5;
import com.aiyi.core.util.thread.ThreadUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

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

    /**
     * 修改用户密码
     * @param user
     *      用户密码
     * @return
     */
    @PutMapping("password")
    public ResultBean updateMyPassword(@RequestBody User user){
        if (!((User)ThreadUtil.getUserEntity()).getPassword().equals(MD5.getMd5(user.getPassword()))){
            throw new ValidationException("旧密码不正确");
        }
        userService.updateMyPassword(user.getNewPassowrd());
        return ResultBean.success();
    }


    /**
     * 更新我的信息
     * @param user
     *      用户信息
     * @return
     */
    @PutMapping("info")
    public ResultBean updateMyInfo(@RequestBody User user){
        userService.updateMyInfo(user);
        return ResultBean.success();
    }

    public static void main(String[] args) {
//        byte[] bytes = "你".getBytes(StandardCharsets.UTF_8);
//        for (byte b: bytes){
//            System.out.println(b + "---" + Integer.toBinaryString(b & 0XFFFF));
//        }
//        System.out.println(8 + "---" + Integer.toBinaryString(8 & 0XFFFF));
        System.out.println(Integer.parseInt("1111", 8));
    }
}
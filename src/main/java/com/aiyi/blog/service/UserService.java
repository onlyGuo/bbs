package com.aiyi.blog.service;

import com.aiyi.blog.entity.User;
import com.aiyi.blog.entity.dto.UserStatistics;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/9 16:52
 * @Email 719348277@qq.com
 * @Description: 用户相关业务处理
 */
public interface UserService {

    /**
     * 用户登录
     * @param user
     *      登录用户信息
     * @return
     */
    String login(User user);

    /**
     * 指定用户退出登录
     * @param userId
     *      用户ID
     */
    void logout(int userId);

    /**
     * 获得用户信息
     * @param phone
     *      用户账号
     * @return
     */
    User getByPhone(String phone);

    /**
     * 用户注册
     * @param user
     *      用户信息
     */
    void register(User user);

    /**
     * 修改用户密码
     * @param newPassowrd
     */
    void updateMyPassword(String newPassowrd);

    /**
     * 更新我的用户信息
     * @param user
     */
    void updateMyInfo(User user);

    /**
     * 获取用户的统计信息
     * @param userId
     *      用户ID
     * @return
     */
    UserStatistics Statistics(int userId);
}

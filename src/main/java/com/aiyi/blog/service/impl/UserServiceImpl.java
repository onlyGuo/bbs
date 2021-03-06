package com.aiyi.blog.service.impl;

import com.aiyi.blog.conf.CommonAttr;
import com.aiyi.blog.dao.PostDao;
import com.aiyi.blog.dao.PostMessageDao;
import com.aiyi.blog.dao.UserDao;
import com.aiyi.blog.entity.Post;
import com.aiyi.blog.entity.PostMessage;
import com.aiyi.blog.entity.User;
import com.aiyi.blog.entity.UserToken;
import com.aiyi.blog.entity.dto.UserStatistics;
import com.aiyi.blog.service.UserService;
import com.aiyi.blog.util.cache.CacheUtil;
import com.aiyi.blog.util.cache.Key;
import com.aiyi.blog.util.cache.UserTokenCacheUtil;
import com.aiyi.core.beans.Method;
import com.aiyi.core.exception.ValidationException;
import com.aiyi.core.sql.where.C;
import com.aiyi.core.util.MD5;
import com.aiyi.core.util.thread.ThreadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/9 16:54
 * @Email 719348277@qq.com
 * @Description: 用户相关业务实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private PostDao postDao;

    @Resource
    private PostMessageDao postMessageDao;

    @Override
    public String login(User user) {
        User dbUser = userDao.get(Method.where(User::getPhone, C.EQ, user.getPhone()));
        if (null == dbUser){
            throw new ValidationException("用户不存在");
        }
        if (!MD5.getMd5(user.getPassword()).equals(dbUser.getPassword())){
            throw new ValidationException("用户名或密码错误");
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        UserTokenCacheUtil.putUserCache(token, dbUser);
        return token;
    }

    @Override
    public void logout(int userId) {
        UserTokenCacheUtil.clear(userId);
    }

    @Override
    public User getByPhone(String phone) {
        if (StringUtils.isEmpty(phone)){
            return null;
        }
        return userDao.get(Method.where(User::getPhone, C.EQ, phone));
    }

    @Override
    public void register(User user) {
        if (StringUtils.isEmpty(user.getSmsCode())){
            throw new ValidationException("验证码不正确或已过期");
        }
        Integer sms = CacheUtil.get(Key.as(CommonAttr.CACHE.VALIDATION_CODE, "SMS", "Register", user.getPhone()), Integer.class);

        if (null == sms || !sms.toString().equals(user.getSmsCode())){
            throw new ValidationException("验证码不正确或已过期");
        }

        // 注册
        User dbUser = userDao.get(Method.where(User::getPhone, C.EQ, user.getPhone()));
        if (null != dbUser){
            throw new ValidationException("该手机号已被注册");
        }

        user.setPassword(MD5.getMd5(user.getPassword()));
        // 默认昵称
        user.setNicker(user.getPhone().substring(0, 3) + "***" + user.getPhone().substring(8));
        userDao.add(user);

        // 失效验证码
        CacheUtil.expire(Key.as(CommonAttr.CACHE.VALIDATION_CODE, "SMS", "Register", user.getPhone()));
    }

    @Override
    public void updateMyPassword(String newPassowrd) {
        User user = ThreadUtil.getUserEntity();
        user.setPassword(MD5.getMd5(newPassowrd));
        userDao.update(user);
        UserTokenCacheUtil.clear(user.getId());
    }

    @Override
    public void updateMyInfo(User user) {
        User my = ThreadUtil.getUserEntity();
        if (!StringUtils.isEmpty(user.getNicker())){
            my.setNicker(user.getNicker());
        }
        if (!StringUtils.isEmpty(user.getSign())){
            my.setSign(user.getSign());
        }
        if (!StringUtils.isEmpty(user.getHeadImg())){
            my.setHeadImg(user.getHeadImg());
        }
        userDao.update(my);
        UserTokenCacheUtil.updateCacheUser(my);
    }

    @Override
    public UserStatistics Statistics(int userId) {
        long postCount = postDao.count(Method.where(Post::getUserId, C.EQ, userId));
        long loveCount = postMessageDao.count(Method.where(PostMessage::getAuthorUserId, C.EQ, userId)
                .and(PostMessage::getType, C.EQ, CommonAttr.POST_MESSAGE_TYPE.LOVE));
        UserStatistics statistics = new UserStatistics();
        statistics.setLoveCount((int)loveCount);
        statistics.setPostCount((int)postCount);
        return statistics;
    }


}
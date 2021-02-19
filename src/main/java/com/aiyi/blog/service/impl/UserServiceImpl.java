package com.aiyi.blog.service.impl;

import com.aiyi.blog.dao.UserDao;
import com.aiyi.blog.entity.User;
import com.aiyi.blog.service.UserService;
import com.aiyi.blog.util.cache.UserTokenCacheUtil;
import com.aiyi.core.beans.Method;
import com.aiyi.core.exception.ValidationException;
import com.aiyi.core.sql.where.C;
import com.aiyi.core.util.MD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

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

    @Override
    public String login(User user) {
        User dbUser = userDao.get(Method.where(User::getEmail, C.EQ, user.getEmail()));
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


}
package com.aiyi.blog.util.cache;

import com.aiyi.blog.conf.CommonAttr;
import com.aiyi.blog.dao.UserDao;
import com.aiyi.blog.dao.UserTokenDao;
import com.aiyi.blog.entity.User;
import com.aiyi.blog.entity.UserToken;
import com.aiyi.core.beans.Method;
import com.aiyi.core.sql.where.C;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/9 17:22
 * @Email 719348277@qq.com
 * @Description:
 */
public class UserTokenCacheUtil {

    /**
     * 缓存时间(单位: 小时)
     */
    private static final int expire = 2;

    public static UserTokenDao userTokenDao;

    public static UserDao userDao;

    /**
     * 缓存一个token和用户的对应关系
     * @param token
     *      登录令牌
     * @param user
     *      用户对象
     */
    public static void putUserCache(String token, User user){
        clear(user.getId());
        // 缓存2小时
        CacheUtil.put(Key.as(CommonAttr.CACHE.LOGIN_KEY, token), user, TimeUnit.HOURS, expire);
        CacheUtil.put(Key.as(CommonAttr.CACHE.USER_ID_TOKEN, String.valueOf(user.getId())), token, TimeUnit.HOURS, expire);

        // 持久化
        UserToken userToken = new UserToken();
        userToken.setUserId(user.getId());
        userToken.setToken(token);

        UserToken dbUserToken = userTokenDao.get(Method.where(UserToken::getUserId, C.EQ, user.getId()));
        if (null == dbUserToken){
            userTokenDao.add(userToken);
        }else{
            dbUserToken.setToken(userToken.getToken());
            userTokenDao.update(dbUserToken);
        }
    }

    /**
     * 清除用户的登录缓存
     * @param userId
     *      对应的用户ID
     */
    public static void clear(int userId) {
        String token = CacheUtil.get(Key.as(CommonAttr.CACHE.USER_ID_TOKEN, String.valueOf(userId)), String.class);
        if (null != token){
            CacheUtil.expire(Key.as(CommonAttr.CACHE.USER_ID_TOKEN, String.valueOf(userId)));
            CacheUtil.expire(Key.as(CommonAttr.CACHE.LOGIN_KEY, token));
        }
        UserToken dbUserToken = userTokenDao.get(Method.where(UserToken::getUserId, C.EQ, userId));
        if (null != dbUserToken){
            dbUserToken.setToken(null);
            userTokenDao.update(dbUserToken);
        }
    }

    /**
     * 更新缓存中的用户(token对应的用户对象)
     * @param user
     *      新的用户对象
     */
    public static void updateCacheUser(User user) {
        String token = CacheUtil.get(Key.as(CommonAttr.CACHE.USER_ID_TOKEN, String.valueOf(user.getId())), String.class);
        if (null != token){
            CacheUtil.put(Key.as(CommonAttr.CACHE.LOGIN_KEY, token), user, TimeUnit.HOURS, expire);
            CacheUtil.put(Key.as(CommonAttr.CACHE.USER_ID_TOKEN, String.valueOf(user.getId())), token, TimeUnit.HOURS, expire);
        }
    }

    /**
     * 通过Token获得用户信息
     * @param token
     *      token
     * @return
     */
    public static User getUser(String token){
        if (StringUtils.isEmpty(token)){
            return null;
        }
        User user = CacheUtil.get(Key.as(CommonAttr.CACHE.LOGIN_KEY, token), User.class);
        if (null == user){
            UserToken dbUserToken = userTokenDao.get(Method.where(UserToken::getToken, C.EQ, token));
            if (null != dbUserToken){
                user = userDao.get(dbUserToken.getUserId());
            }
        }
        return user;
    }
}
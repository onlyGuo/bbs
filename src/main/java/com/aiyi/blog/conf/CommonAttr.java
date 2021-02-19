package com.aiyi.blog.conf;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/9 17:08
 * @Email 719348277@qq.com
 * @Description: 公共参数
 */
public interface CommonAttr {

    /**
     * 缓存相关KEY
     */
    interface CACHE{

        /**
         * 登录KEY - TOKEN对应的用户对象
         */
        String LOGIN_KEY = "LOGIN_KEY";

        /**
         * 用户ID对应的TOKEN
         */
        String USER_ID_TOKEN = "USER_ID_TOKEN";

        /**
         * 网站设置
         */
        String WEB_SITE = "WEB_SITE";

        /**
         * 验证码
         */
        String VALIDATION_CODE = "VALI_CODE";
    }

    /**
     * 开关等逻辑功能状态
     */
    interface SWITCH{
        /**
         * 开启
         */
        int ON = 1;
        /**
         * 关闭
         */
        int OFF = 0;
    }

}
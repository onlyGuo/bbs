package com.aiyi.blog.service;

import com.aiyi.blog.entity.WebSite;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/15 08:04
 * @Email 719348277@qq.com
 * @Description: 网站设置相关业务
 */
public interface WebSiteService {

    /**
     * 获取当前网站设置
     * @return
     */
    WebSite getWebSite();

    /**
     * 更新网站设置
     * @param webSite
     */
    void updateWebSite(WebSite webSite);

}

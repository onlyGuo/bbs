package com.aiyi.blog.service.impl;

import com.aiyi.blog.conf.CommonAttr;
import com.aiyi.blog.dao.WebSiteDao;
import com.aiyi.blog.entity.WebSite;
import com.aiyi.blog.service.WebSiteService;
import com.aiyi.blog.util.cache.CacheUtil;
import com.aiyi.blog.util.cache.Key;
import com.aiyi.core.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/15 08:07
 * @Email 719348277@qq.com
 * @Description: 网站设置相关业务实现
 */
@Service
public class WebSiteServiceImpl implements WebSiteService {

    @Resource
    private WebSiteDao webSiteDao;

    @Override
    public WebSite getWebSite() {
        WebSite webSite = CacheUtil.get(Key.as(CommonAttr.CACHE.WEB_SITE), WebSite.class);
        if (null == webSite){
            webSite = webSiteDao.get(1);
        }
        if (null == webSite){
            webSite = new WebSite();
            webSite.setCacheTime(0);
            webSite.setHomeTitle("首页");
            webSite.setId(1);
            webSite.setLicense("© 2020 321aiyi.com GPL3.0 license NPL996 license");
            webSite.setMaxUpdateSize(0);
            webSite.setMetaDescription("这是一个基于Spring boot的开源博客项目. 开源协议GPL3.0 NPL996. GitHub:https://github.com/onlyGuo/blog");
            webSite.setMetaKeywords("blog, java, spring, boot, 文章, 开源, 博客");
            webSite.setName("天涯客栈");
            webSite.setUploadFileType("JAVA|JPEG|PNG|GIF|ZIP|GZ|TAR|TXT");
            webSite.setDomain("localhost");
            webSiteDao.add(webSite);
        }
        // 缓存120秒
        CacheUtil.put(Key.as(CommonAttr.CACHE.WEB_SITE), webSite, TimeUnit.SECONDS, 120);
        return webSite;
    }

    @Override
    public void updateWebSite(WebSite webSite) {
        webSite.setId(1);
        webSiteDao.update(webSite);
        // 清除缓存
        CacheUtil.expire(Key.as(CommonAttr.CACHE.WEB_SITE));
    }

}
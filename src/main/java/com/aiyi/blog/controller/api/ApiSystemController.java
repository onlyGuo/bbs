package com.aiyi.blog.controller.api;

import com.aiyi.blog.entity.WebSite;
import com.aiyi.blog.service.WebSiteService;
import com.aiyi.core.beans.ResultBean;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/15 11:21
 * @Email 719348277@qq.com
 * @Description: 系统相关API
 */
@RestController
@RequestMapping("api/v1/system")
public class ApiSystemController {

    @Resource
    private WebSiteService webSiteService;

    /**
     * 修改网站设置
     * @param webSite
     *      网站设置信息
     * @return
     */
    @PutMapping("website")
    public ResultBean updateWebSite(@RequestBody WebSite webSite){
        webSiteService.updateWebSite(webSite);
        return ResultBean.success();
    }

}
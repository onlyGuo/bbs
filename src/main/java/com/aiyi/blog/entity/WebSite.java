package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.FieldName;
import com.aiyi.core.annotation.po.ID;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.beans.PO;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/14 11:37
 * @Email 719348277@qq.com
 * @Description: 网站设置
 */
@TableName(name = "blog_webset")
public class WebSite extends PO {

    @ID
    private int id;

    /**
     * 网站名称
     */
    private String name;

    /**
     * 网站域名
     */
    private String domain;

    /**
     * 缓存时间
     */
    @FieldName(name = "cache_time")
    private int cacheTime;

    /**
     * 最大上传大小
     */
    @FieldName(name = "max_update_size")
    private int maxUpdateSize;

    /**
     * 允许上传文件类型
     */
    @FieldName(name = "upload_file_type")
    private String uploadFileType;

    /**
     * 主页标题
     */
    @FieldName(name = "home_title")
    private String homeTitle;

    /**
     * 头关键字信息
     */
    @FieldName(name = "meta_keywords")
    private String metaKeywords;

    /**
     * 头描述信息
     */
    @FieldName(name = "meta_description")
    private String metaDescription;

    /**
     * 授权信息
     */
    private String license;

    /**
     * 默认昵称
     */
    private String nicker;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(int cacheTime) {
        this.cacheTime = cacheTime;
    }

    public int getMaxUpdateSize() {
        return maxUpdateSize;
    }

    public void setMaxUpdateSize(int maxUpdateSize) {
        this.maxUpdateSize = maxUpdateSize;
    }

    public String getUploadFileType() {
        return uploadFileType;
    }

    public void setUploadFileType(String uploadFileType) {
        this.uploadFileType = uploadFileType;
    }

    public String getHomeTitle() {
        return homeTitle;
    }

    public void setHomeTitle(String homeTitle) {
        this.homeTitle = homeTitle;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getNicker() {
        return nicker;
    }

    public void setNicker(String nicker) {
        this.nicker = nicker;
    }
}
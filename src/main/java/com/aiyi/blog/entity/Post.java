package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.*;
import com.aiyi.core.beans.PO;

import java.util.Date;
import java.util.List;
import java.util.Map;

@TableName(name = "bbs_post")
public class Post extends PO {

    @ID
    private long id;

    @FieldName(name = "user_id")
    private int userId;

    /**
     * 发布人昵称
     */
    @FieldName(name = "user_nicker")
    private String userNicker;

    /**
     * 发布人头像
     */
    @FieldName(name = "user_head_img")
    private String userHeadImg;

    /**
     * 0 = 普通动态（文字+图片）， 1 = 文章
     */
    private int type;

    /**
     * 帖子标题（type=1时有效）
     */
    private String title;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 图片列表
     */
    @JsonField
    private List<String> imgs;

    /**
     * 发布时间
     */
    @FieldName(name = "create_time")
    private Date createTime;

    /**
     * 点赞数量
     */
    @FieldName(name = "love_count")
    private int loveCount;

    /**
     * 评论数量
     */
    @FieldName(name = "comment_count")
    private int commentCount;

    /**
     * 经度
     */
    private double lon;

    /**
     * 维度
     */
    private double lat;

    /**
     * 城市名称
     */
    @FieldName(name = "city_name")
    private String cityName;

    /**
     * 我与这个帖子发布地点的相对距离（米）
     */
    @TempField
    private double distance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserNicker() {
        return userNicker;
    }

    public void setUserNicker(String userNicker) {
        this.userNicker = userNicker;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(int loveCount) {
        this.loveCount = loveCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

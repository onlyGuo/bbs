package com.aiyi.blog.entity.dto;

/**
 * 用户统计相关
 */
public class UserStatistics {

    /**
     * 发帖数
     */
    private int postCount;

    /**
     * 被赞数
     */
    private int loveCount;

    /**
     * 积分
     */
    private int money;

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(int loveCount) {
        this.loveCount = loveCount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

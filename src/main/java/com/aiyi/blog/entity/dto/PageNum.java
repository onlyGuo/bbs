package com.aiyi.blog.entity.dto;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/24 14:51
 * @Email 719348277@qq.com
 * @Description: 页码
 */
public class PageNum {

    private int num;

    private boolean thisPage;

    private boolean abst;

    private boolean frist;

    private boolean last;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isThisPage() {
        return thisPage;
    }

    public void setThisPage(boolean thisPage) {
        this.thisPage = thisPage;
    }

    public boolean isAbst() {
        return abst;
    }

    public void setAbst(boolean abst) {
        this.abst = abst;
    }

    public boolean isFrist() {
        return frist;
    }

    public void setFrist(boolean frist) {
        this.frist = frist;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}

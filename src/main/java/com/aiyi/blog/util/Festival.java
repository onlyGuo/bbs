package com.aiyi.blog.util;

import java.util.Date;

/**
 * 节日信息
 */
public class Festival {
    Festival(){}
    Festival(String name, String date, String lunar, String greetings){
        this.date = date;
        this.greetings = greetings;
        this.lunar = lunar;
        this.name = name;
    }

    /**
     * 节日名称
     */
    private String name;

    /**
     * 节日时间
     */
    private String date;

    /**
     * 节日农历时间
     */
    private String lunar;

    /**
     * 节日问候语
     */
    private String greetings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }
}

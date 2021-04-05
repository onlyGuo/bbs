package com.aiyi.blog.util;

import java.util.Date;

public class DateStrUtil {

    /**
     * 获得距离指定时间的文字描述距离
     * @param date
     *      当前时间距离目标时间
     * @return
     */
    public static String strLength(Date date){
        long l = (System.currentTimeMillis() - date.getTime()) / 1000;
        l = l < 0 ? -l : l;
        if (l < 60){
            return "刚刚";
        }else if (l < 3600){
            return l / 60 + "分钟前";
        }else if (l < 86400){
            return l / 3600 + "小时前";
        }else if (l < 2592000){
            return l / 86400 + "天前";
        }else if (l < 31104000){
            return l / 2592000 + "个月前";
        }else{
            return l / 31104000 + "年前";
        }
    }
}

package com.aiyi.blog.util;

import com.aiyi.core.util.DateUtil;
import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FestivalUtil {

    private static Map<String, Festival> festivalMap = new HashMap<>();
    static {
        festivalMap.put("01-01", new Festival("元旦", "01-01", null, "新的一年祝你元旦快乐"));
        festivalMap.put("腊月初八", new Festival("腊八节", null, "腊月初八", "腊八节快乐"));
        festivalMap.put("02-14", new Festival("情人节", "02-14", null, "今天是情人节哦"));
        festivalMap.put("腊月三十", new Festival("除夕", null, "腊月三十", "在烟花璀璨的除夕夜里想你"));
        festivalMap.put("正月初一", new Festival("春节", null, "正月初一", "春节快乐，合家团员"));
        festivalMap.put("正月十五", new Festival("元宵节", null, "正月十五", "想煮元宵给你却不知你在哪"));
        festivalMap.put("03-08", new Festival("妇女节", "03-08", null, "妇女节快乐！"));
        festivalMap.put("04-01", new Festival("愚人节", "04-01", null, "我只为你服务，愚人节快乐"));
        festivalMap.put("04-05", new Festival("清明节", "04-05", null, "让风筝飞，飞走忧愁"));
        festivalMap.put("05-01", new Festival("劳动节", "05-01", null, "五一快乐，想好去哪了嘛"));
        festivalMap.put("05-06", new Festival("立夏", "05-06", null, "立夏了，喜欢你的长裙飘摆"));
        festivalMap.put("06-01", new Festival("儿童节", "06-01", null, "儿童节快乐，你的小宝贝呢"));
        festivalMap.put("五月初五", new Festival("端午节", null, "五月初五", "粽子包裹我对你的祝福"));
        festivalMap.put("07-23", new Festival("大薯", "07-23", null, "大薯已至，注意防暑"));
        festivalMap.put("08-01", new Festival("建军节", "08-01", null, "想穿上那帅气的军装保护你"));
        festivalMap.put("08-08", new Festival("立秋", "08-08", null, "立秋了，记得多加件衣服"));
        festivalMap.put("七月初七", new Festival("七夕节", null, "七月初七", "这是一年中最浪漫的一天"));
        festivalMap.put("七月十五", new Festival("中元节", null, "七月十五", "思念随风倾诉，别玩太晚"));
        festivalMap.put("09-03", new Festival("中国人民抗日战争胜利纪念日", "09-03", null, "一寸山河一寸血"));
        festivalMap.put("八月十五", new Festival("中秋节", null, "八月十五", "月亮…不,月饼代表我的心"));
        festivalMap.put("10-01", new Festival("国庆节", "10-01", null, "愿你国庆假期格外精彩"));
        festivalMap.put("10-08", new Festival("寒露", "10-08", null, "天气转凉了，记得加衣"));
        festivalMap.put("九月初九", new Festival("重阳节", null, "九月初九", "佳节思亲，而我思你"));
        festivalMap.put("11-01", new Festival("万圣节", "11-01", null, "万圣节快乐"));
        festivalMap.put("11-08", new Festival("立冬", "11-08", null, "立冬了，别冻着我会心疼"));
        festivalMap.put("11-26", new Festival("感恩节", "11-26", null, "感恩节，感谢你的聆听"));
        festivalMap.put("12-07", new Festival("大雪", "12-07", null, "大雪将至，注意防寒"));
        festivalMap.put("12-13", new Festival("南京大屠杀死难者国家公祭日", "12-13", null, "勿忘国耻，警钟长鸣"));
        festivalMap.put("12-24", new Festival("平安夜", "12-24", null, "把苹果送给你，平安夜快乐"));
        festivalMap.put("12-25", new Festival("圣诞节", "12-25", null, "圣诞节快乐"));
    }

    public static Festival getFestival(Date date){
        String time = DateUtil.formatAll(date).substring(5, 10);
        String launarTime = LunarUtil.getLunar(date).toString().substring(5);
        Festival festival = festivalMap.get(time);
        if (null == festival){
            festival = festivalMap.get(launarTime);
        }
        return festival;
    }

    public static void main(String[] args) {
        Festival festival = getFestival(new Date(System.currentTimeMillis() - DateUtil.DAY_TIME * 4));
        if(null == festival){
            return;
        }
        System.out.println(JSON.toJSONString(festival));
    }

}

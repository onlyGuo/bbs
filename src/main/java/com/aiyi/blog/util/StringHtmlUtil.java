package com.aiyi.blog.util;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/16 16:14
 * @Email 719348277@qq.com
 * @Description: HTML 大纲
 */
public class StringHtmlUtil {

    /**
     * 返回HTML大纲
     * @param html
     *      html
     * @return
     */
    public static String outline(String html){
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        String regEx_html = "<[^>]+>";
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(html);
        String htmlStr = m_script.replaceAll("");
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");
        htmlStr = htmlStr.trim().replace("　", "");
        if (htmlStr.length() > 255){
            htmlStr = htmlStr.substring(0, 255 - 3) + "...";
        }
        return htmlStr;
    }

    public static void main(String[] args) {
        System.out.println(outline("<p>啊啊啊啊啊</p>\n" +
                "<h1 id=\"h1-u554Au554Au554Au554Au554Au554A\"><a name=\"啊啊啊啊啊啊\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>啊啊啊啊啊啊</h1><blockquote>\n" +
                "<p>sdasd</p>\n" +
                "</blockquote>\n" +
                "<pre><code class=\"lang-java\">public static void main(String[] args){\n" +
                "    System.out.println(&quot;hello&quot;);\n" +
                "}\n" +
                "</code></pre>\n"));
    }




}
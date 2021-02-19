package com.aiyi.blog.dao;

import com.aiyi.blog.entity.Article;
import com.aiyi.core.beans.ResultPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/16 18:17
 * @Email 719348277@qq.com
 * @Description: 文章表操作
 */
@Mapper
public interface ArticleMapper {


    /**
     * 条件列出文章列表
     * @param title
     *      文章标题
     * @param classifyId
     *      所属分类
     * @param start
     *      页码
     * @param length
     *      每页条数
     * @return
     */
    @Select("<script>SELECT\n" +
            "    blog_article.id AS id,\n" +
            "    blog_article.title AS title,\n" +
            "    blog_article.outline AS outline,\n" +
            "    blog_article.create_time AS createTime,\n" +
            "    blog_article.update_time AS updateTime,\n" +
            "    blog_article.display_count AS displayCount,\n" +
            "    blog_article.cover_image AS coverImage,\n" +
            "    GROUP_CONCAT(blog_classify.`name`) AS classifyName\n" +
            "FROM\n" +
            "    blog_article AS blog_article\n" +
            "\n" +
            "LEFT JOIN\n" +
            "    blog_article_classify\n" +
            "        ON  blog_article_classify.article_id = blog_article.id\n" +
            "LEFT JOIN\n" +
            "    blog_classify\n" +
            "        ON blog_classify.id = blog_article_classify.classify_id\n" +
            "WHERE 1=1 <if test=\"title != null and title != ''\"> AND blog_article.title LIKE '%${title}%'</if>\n" +
            " <if test=\"classifyId != null\"> AND blog_article_classify.classify_id = #{classifyId} </if>" +
            "GROUP BY blog_article.id ORDER BY id DESC\n" +
            "LIMIT #{start}, #{length}</script>")
    List<Article> list(String title, Integer classifyId, int start, int length);

    /**
     * 列出符合条件的文章数量
     * @param title
     *      文章标题
     * @param classifyId
     *      所属分类
     * @return
     */
    @Select("<script>SELECT COUNT(*) FROM (SELECT blog_article.id FROM\n" +
            "    blog_article AS blog_article\n" +
            "\n" +
            "LEFT JOIN\n" +
            "    blog_article_classify\n" +
            "        ON  blog_article_classify.article_id = blog_article.id\n" +
            "WHERE 1=1 <if test=\"title != null and title != ''\"> AND blog_article.title LIKE '%${title}%'</if>\n" +
            " <if test=\"classifyId != null\"> AND blog_article_classify.classify_id = #{classifyId} </if>" +
            "GROUP BY blog_article.id)a</script>")
    int count(String title, Integer classifyId);

    /**
     * 分页列出符合条件的文章列表
     * @param title
     *      文章标题
     * @param classifyId
     *      所属分类
     * @param page
     *      页码
     * @param pageSize
     *      每页条数
     * @return
     */
    default ResultPage<Article> listByPage(String title, Integer classifyId, int page, int pageSize){
        ResultPage<Article> resultPage = new ResultPage<>(count(title, classifyId), page, pageSize,
                list(title, classifyId, (page - 1) * pageSize, pageSize));
        return resultPage;
    }


}

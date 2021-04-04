package com.aiyi.blog.service;

import com.aiyi.blog.entity.Post;
import com.aiyi.core.beans.ResultPage;

import java.util.List;

/**
 * 帖子相关业务
 */
public interface PostService {

    /**
     * 发布帖子
     * @param post
     *      帖子信息
     * @return
     */
    Post post(Post post);

    /**
     * 列出某个用户的帖子列表
     * @param userId
     *      用户ID
     * @param page
     *      页码
     * @param pageSize
     *      每页条数
     * @return
     */
    ResultPage<Post> list(int userId, int page, int pageSize);

    /**
     * 列出广场的帖子（优先列出同城帖子）
     * @param page
     *      页码
     * @param pageSize
     *      每页条数
     * @param lon
     *      经度
     * @param lat
     *      纬度
     * @param cityName
     *      我所在的城市名称
     * @return
     */
    ResultPage<Post> list(int page, int pageSize, double lon, double lat, String cityName, boolean top, int tagId);

    /**
     * 删除一个我自己的帖子
     * @param post
     *      帖子信息
     */
    void deleteMy(Post post);

    /**
     * 帖子点赞/取消赞
     * @param id
     *      帖子ID
     * @return
     *      0 = 已取消， 1 = 已点赞
     */
    int love(long id);

    /**
     * 帖子详情
     * @param id
     *      帖子ID
     * @return
     */
    Post info(long id, double lon, double lat);
}

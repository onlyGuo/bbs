package com.aiyi.blog.service;

import com.aiyi.blog.entity.Post;

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

}

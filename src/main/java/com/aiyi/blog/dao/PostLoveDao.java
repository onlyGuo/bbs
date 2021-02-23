package com.aiyi.blog.dao;

import com.aiyi.blog.entity.PostLove;
import com.aiyi.core.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 帖子点赞记录列表
 */
@Repository
public class PostLoveDao extends BaseDaoImpl<PostLove, Long> {
}

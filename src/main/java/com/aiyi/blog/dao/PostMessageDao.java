package com.aiyi.blog.dao;

import com.aiyi.blog.entity.PostMessage;
import com.aiyi.core.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 帖子消息表操作类
 */
@Repository
public class PostMessageDao extends BaseDaoImpl<PostMessage, Long> {
}

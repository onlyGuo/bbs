package com.aiyi.blog.service.impl;

import com.aiyi.blog.conf.CommonAttr;
import com.aiyi.blog.dao.PostMessageDao;
import com.aiyi.blog.entity.PostMessage;
import com.aiyi.blog.entity.dto.PostNoReadMessage;
import com.aiyi.blog.service.PostMessageService;
import com.aiyi.core.beans.Method;
import com.aiyi.core.sql.where.C;
import com.aiyi.core.util.thread.ThreadUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PostMessageServiceImpl implements PostMessageService {

    /**
     * 帖子消息表操作类
     */
    @Resource
    private PostMessageDao postMessageDao;

    @Override
    public void sendMessage(PostMessage message) {
        postMessageDao.add(message);
    }

    @Override
    public void readAll(int type) {
        postMessageDao.execute("UPDATE " + postMessageDao.tableName()
                + " SET read = true WHERE author_user_id = ?", ThreadUtil.getUserId());
    }

    @Override
    public PostNoReadMessage noRead() {
        PostNoReadMessage message = new PostNoReadMessage();

        long at = postMessageDao.count(Method.where(PostMessage::getAuthorUserId, C.EQ, ThreadUtil.getUserId())
                .and(PostMessage::getType, C.EQ, CommonAttr.POST_MESSAGE_TYPE.AT));
        long comment = postMessageDao.count(Method.where(PostMessage::getAuthorUserId, C.EQ, ThreadUtil.getUserId())
                .and(PostMessage::getType, C.EQ, CommonAttr.POST_MESSAGE_TYPE.COMMENT));
        long love = postMessageDao.count(Method.where(PostMessage::getAuthorUserId, C.EQ, ThreadUtil.getUserId())
                .and(PostMessage::getType, C.EQ, CommonAttr.POST_MESSAGE_TYPE.LOVE));

        message.setAt((int) at);
        message.setComment((int) comment);
        message.setLove((int) love);
        return message;
    }
}

package com.aiyi.blog.service.impl;

import com.aiyi.blog.conf.CommonAttr;
import com.aiyi.blog.dao.PostMessageDao;
import com.aiyi.blog.entity.PostMessage;
import com.aiyi.blog.entity.dto.PostNoReadMessage;
import com.aiyi.blog.service.PostMessageService;
import com.aiyi.blog.util.cache.CacheUtil;
import com.aiyi.blog.util.cache.Key;
import com.aiyi.core.beans.Method;
import com.aiyi.core.sql.where.C;
import com.aiyi.core.util.thread.ThreadUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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
        Key key = Key.as(CommonAttr.CACHE.POST_NOREAD_MESSAGE, ThreadUtil.getUserId().toString());
        PostNoReadMessage cacheMessage = CacheUtil.get(key, PostNoReadMessage.class);
        if (null != cacheMessage){
            return cacheMessage;
        }

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

        CacheUtil.put(key, message, TimeUnit.HOURS, 1);
        return message;
    }
}

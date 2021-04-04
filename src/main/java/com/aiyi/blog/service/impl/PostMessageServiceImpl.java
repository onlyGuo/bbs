package com.aiyi.blog.service.impl;

import com.aiyi.blog.conf.CommonAttr;
import com.aiyi.blog.dao.PostMessageDao;
import com.aiyi.blog.entity.PostMessage;
import com.aiyi.blog.entity.dto.PostNoReadMessage;
import com.aiyi.blog.service.PostMessageService;
import com.aiyi.blog.util.cache.CacheUtil;
import com.aiyi.blog.util.cache.Key;
import com.aiyi.core.beans.Method;
import com.aiyi.core.beans.ResultPage;
import com.aiyi.core.beans.Sort;
import com.aiyi.core.enums.OrderBy;
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
    public PostNoReadMessage readAll(int type) {
        postMessageDao.execute("UPDATE " + postMessageDao.tableName()
                + " SET `read` = true WHERE author_user_id = ? AND `read` = false", ThreadUtil.getUserId());
        return getNoReadInDb();
    }

    @Override
    public PostNoReadMessage noRead() {
        Key key = Key.as(CommonAttr.CACHE.POST_NOREAD_MESSAGE, ThreadUtil.getUserId().toString());
        PostNoReadMessage cacheMessage = CacheUtil.get(key, PostNoReadMessage.class);
        if (null != cacheMessage){
            return cacheMessage;
        }
        return getNoReadInDb();
    }


    private PostNoReadMessage getNoReadInDb(){
        PostNoReadMessage message = new PostNoReadMessage();
        long at = postMessageDao.count(Method.where(PostMessage::getAuthorUserId, C.EQ, ThreadUtil.getUserId())
                .and(PostMessage::getType, C.EQ, CommonAttr.POST_MESSAGE_TYPE.AT)
                .and(PostMessage::isHasRead, C.EQ, false));
        long comment = postMessageDao.count(Method.where(PostMessage::getAuthorUserId, C.EQ, ThreadUtil.getUserId())
                .and(PostMessage::getType, C.EQ, CommonAttr.POST_MESSAGE_TYPE.COMMENT)
                .and(PostMessage::isHasRead, C.EQ, false));
        long love = postMessageDao.count(Method.where(PostMessage::getAuthorUserId, C.EQ, ThreadUtil.getUserId())
                .and(PostMessage::getType, C.EQ, CommonAttr.POST_MESSAGE_TYPE.LOVE)
                .and(PostMessage::isHasRead, C.EQ, false));

        message.setAt((int) at);
        message.setComment((int) comment);
        message.setLove((int) love);
        Key key = Key.as(CommonAttr.CACHE.POST_NOREAD_MESSAGE, ThreadUtil.getUserId().toString());
        CacheUtil.put(key, message, TimeUnit.HOURS, 1);
        return message;
    }

    @Override
    public ResultPage<PostMessage> list(int type, int page, int pageSize) {
        return postMessageDao.list(Method.where(PostMessage::getType, C.EQ, type)
                .and(PostMessage::getAuthorUserId, C.EQ, ThreadUtil.getUserId())
                .orderBy(Sort.of(PostMessage::getId, OrderBy.DESC)), page, pageSize);
    }
}

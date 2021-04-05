package com.aiyi.blog.service.impl;

import com.aiyi.blog.conf.CommonAttr;
import com.aiyi.blog.dao.PostDao;
import com.aiyi.blog.dao.PostLoveDao;
import com.aiyi.blog.dao.PostMessageDao;
import com.aiyi.blog.entity.Post;
import com.aiyi.blog.entity.PostLove;
import com.aiyi.blog.entity.PostMessage;
import com.aiyi.blog.entity.User;
import com.aiyi.blog.entity.dto.PostNoReadMessage;
import com.aiyi.blog.service.PostMessageService;
import com.aiyi.blog.service.PostService;
import com.aiyi.blog.util.MapUtils;
import com.aiyi.blog.util.cache.CacheUtil;
import com.aiyi.blog.util.cache.Key;
import com.aiyi.core.beans.*;
import com.aiyi.core.enums.OrderBy;
import com.aiyi.core.exception.ValidationException;
import com.aiyi.core.sql.where.C;
import com.aiyi.core.util.thread.ThreadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 帖子相关业务实现
 */
@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostDao postDao;

    @Resource
    private PostLoveDao postLoveDao;

    @Resource
    private PostMessageService postMessageService;

    @Resource
    private PostMessageDao postMessageDao;

    @Override
    public Post post(Post post) {
        if (StringUtils.isEmpty(post.getContent())){
            throw new ValidationException("内容不能为空");
        }
        User user = ThreadUtil.getUserEntity();
        post.setUserId(user.getId());
        post.setUserHeadImg(user.getHeadImg());
        post.setUserNicker(user.getNicker());
        post.setCreateTime(new Date());
        if (post.getType() == 1){
            if (StringUtils.isEmpty(post.getTitle())){
                throw new ValidationException("文章标题不能为空");
            }
        }
        postDao.add(post);
        return post;
    }

    @Override
    public ResultPage<Post> list(int userId, int page, int pageSize) {
        return postDao.list(Method.where(Post::getUserId, C.EQ, userId).and(Post::isDeleted, C.EQ, false)
                        .orderBy(Sort.of(Post::getId, OrderBy.DESC)),
                LeftJoin.join(PostLove.class, Method.where(PostLove::getPostId, C.EQ, Post::getId)
                        .and(PostLove::getUserId, C.EQ, ThreadUtil.getUserId()), PostLove::isLove),
                page, pageSize);
    }

    @Override
    public ResultPage<Post> list(int page, int pageSize, double lon, double lat, String cityName, boolean top, int tagId) {
        WherePrams where = Method.where(Post::isDeleted, C.EQ, false);
        if (!StringUtils.isEmpty(cityName)){
            where.orderBy("field(city_name, '" + cityName + "')DESC, create_time DESC");
        }else{
            where.orderBy(Sort.of(Post::getCreateTime, OrderBy.DESC));
        }
        if (top){
            where.and(Post::isTop, C.EQ, true);
        }
        if (tagId > 0){
            where.and(Post::getTags, C.LIKE, "\"" + tagId + "\"");
        }
        ResultPage<Post> list = postDao.list(where,
                LeftJoin.join(PostLove.class, Method.where(PostLove::getPostId, C.EQ, Post::getId)
                        .and(PostLove::getUserId, C.EQ, ThreadUtil.getUserId()), PostLove::isLove), page, pageSize);

        if (lon > 0 && lat > 0){
            // 计算距离
            for (Post post: list.getList()){
                if (post.getLat() > 0 && post.getLon() > 0){
                    post.setDistance(MapUtils.GetDistance(lat, lon, post.getLat(), post.getLon()));
                }
            }
        }
        return list;
    }

    @Override
    public void deleteMy(Post post) {
        Post dbPost = postDao.get(Method.where(Post::getId, C.EQ, post)
                .and(Post::getUserId, C.EQ, ThreadUtil.getUserId()).and(Post::isDeleted, C.EQ, false));
        if (null != dbPost){
            dbPost.setDeleted(true);
            postDao.update(dbPost);
        }
    }

    @Override
    public int love(long id) {
        int userId = Integer.parseInt(ThreadUtil.getUserId().toString());
        User user = ThreadUtil.getUserEntity();
        return CacheUtil.lock(Key.as("POST.LOVE", String.valueOf(id), ThreadUtil.getUserId().toString()), () -> {
            PostLove postLove = postLoveDao.get(Method.where(PostLove::getPostId, C.EQ, id)
                    .and(PostLove::getUserId, C.EQ, userId));
            if (null == postLove){
                // 没赞就点赞
                postLove = new PostLove();
                postLove.setPostId(id);
                postLove.setUserId(userId);
                postLoveDao.add(postLove);
                postDao.execute("UPDATE " + postDao.tableName() + " SET love_count = love_count + 1 WHERE id = ?", id);

                // 增加点赞通知
                Post post = postDao.get(id);
                if (null != post && !post.isDeleted()){
                    PostMessage message = new PostMessage();
                    message.setAuthorUserId(post.getUserId());
                    message.setPostId(id);
                    message.setUserId(user.getId());
                    message.setUserNicker(user.getNicker());
                    message.setType(CommonAttr.POST_MESSAGE_TYPE.LOVE);
                    message.setUserHeaderImg(user.getHeadImg());
                    postMessageService.sendMessage(message);
                    Key key = Key.as(CommonAttr.CACHE.POST_NOREAD_MESSAGE, "" + userId);
                    PostNoReadMessage cacheMessage = CacheUtil.get(key, PostNoReadMessage.class);
                    if (null != cacheMessage){
                        cacheMessage.setLove(cacheMessage.getLove() + 1);
                        CacheUtil.put(key, cacheMessage, TimeUnit.HOURS, 1);
                    }

                }

                return 1;
            }else{
                // 有赞就取消
                postLoveDao.del(postLove.getId());
                postDao.execute("UPDATE " + postDao.tableName() + " SET love_count = love_count - 1 WHERE id = ?", id);
                return 0;
            }
        });
    }

    @Override
    public Post info(long id, double lon, double lat) {
        Post post = postDao.get(id);
        if (lon > 0 && lat > 0){
            // 计算距离
            if (post.getLat() > 0 && post.getLon() > 0){
                post.setDistance(MapUtils.GetDistance(lat, lon, post.getLat(), post.getLon()));
            }
        }
        return post;
    }

    @Override
    public PostNoReadMessage comment(PostMessage message) {
        PostNoReadMessage message1 = postMessageService.sendMessage(message);
        Post post = postDao.get(message.getPostId());
        post.setCommentCount(post.getCommentCount() + 1);
        postDao.update(post);
        return message1;
    }

    @Override
    public ResultPage<PostMessage> listComment(long postId, int page, int pageSize) {
        return postMessageDao.list(Method.where(PostMessage::getPostId, C.EQ, postId)
                .orderBy(Sort.of(PostMessage::getId, OrderBy.DESC)), page, pageSize);
    }
}

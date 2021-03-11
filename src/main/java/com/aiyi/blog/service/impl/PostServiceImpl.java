package com.aiyi.blog.service.impl;

import com.aiyi.blog.dao.PostDao;
import com.aiyi.blog.dao.PostLoveDao;
import com.aiyi.blog.entity.Post;
import com.aiyi.blog.entity.PostLove;
import com.aiyi.blog.entity.User;
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

/**
 * 帖子相关业务实现
 */
@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostDao postDao;

    @Resource
    private PostLoveDao postLoveDao;

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
        return postDao.list(Method.where(Post::getUserId, C.EQ, userId).and(Post::isDeleted, C.EQ, false),
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
        return CacheUtil.lock(Key.as("POST.LOVE", String.valueOf(id), ThreadUtil.getUserId().toString()), () -> {
            PostLove postLove = postLoveDao.get(Method.where(PostLove::getPostId, C.EQ, id)
                    .and(PostLove::getUserId, C.EQ, ThreadUtil.getUserId()));
            if (null == postLove){
                // 没赞就点赞
                postLove = new PostLove();
                postLove.setPostId(id);
                postLove.setUserId(userId);
                postLoveDao.add(postLove);
                postDao.execute("UPDATE " + postDao.tableName() + " SET love_count = love_count + 1 WHERE id = ?", id);
                return 1;
            }else{
                // 有赞就取消
                postLoveDao.del(postLove.getId());
                postDao.execute("UPDATE " + postDao.tableName() + " SET love_count = love_count - 1 WHERE id = ?", id);
                return 0;
            }
        });
    }
}

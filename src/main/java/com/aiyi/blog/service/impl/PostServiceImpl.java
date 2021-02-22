package com.aiyi.blog.service.impl;

import com.aiyi.blog.dao.PostDao;
import com.aiyi.blog.entity.Post;
import com.aiyi.blog.entity.User;
import com.aiyi.blog.service.PostService;
import com.aiyi.blog.util.MapUtils;
import com.aiyi.core.beans.Method;
import com.aiyi.core.beans.ResultPage;
import com.aiyi.core.beans.Sort;
import com.aiyi.core.beans.WherePrams;
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
        return postDao.list(Method.where(Post::getUserId, C.EQ, userId), page, pageSize);
    }

    @Override
    public ResultPage<Post> list(int page, int pageSize, double lon, double lat, String cityName) {
        WherePrams where = Method.createDefault();
        if (!StringUtils.isEmpty(cityName)){
            where.orderBy("field(city_name, '" + cityName + "')DESC, create_time DESC");
        }else{
            where.orderBy(Sort.of(Post::getCreateTime, OrderBy.DESC));
        }
        ResultPage<Post> list = postDao.list(where, page, pageSize);

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
}

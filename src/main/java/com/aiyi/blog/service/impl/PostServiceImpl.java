package com.aiyi.blog.service.impl;

import com.aiyi.blog.dao.PostDao;
import com.aiyi.blog.entity.Post;
import com.aiyi.blog.entity.User;
import com.aiyi.blog.service.PostService;
import com.aiyi.core.exception.ValidationException;
import com.aiyi.core.util.thread.ThreadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

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
        if (post.getType() == 1){
            if (StringUtils.isEmpty(post.getTitle())){
                throw new ValidationException("文章标题不能为空");
            }
        }
        postDao.add(post);
        return post;
    }
}

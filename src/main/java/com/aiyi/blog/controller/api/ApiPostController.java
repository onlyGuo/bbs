package com.aiyi.blog.controller.api;

import com.aiyi.blog.conf.CommonAttr;
import com.aiyi.blog.dao.TagDao;
import com.aiyi.blog.entity.Post;
import com.aiyi.blog.entity.PostMessage;
import com.aiyi.blog.entity.Tag;
import com.aiyi.blog.entity.User;
import com.aiyi.blog.entity.dto.PostNoReadMessage;
import com.aiyi.blog.service.PostMessageService;
import com.aiyi.blog.service.PostService;
import com.aiyi.core.beans.Method;
import com.aiyi.core.beans.ResultBean;
import com.aiyi.core.sql.where.C;
import com.aiyi.core.util.thread.ThreadUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 帖子相关
 */
@RestController
@RequestMapping("api/v1/post")
public class ApiPostController {

    @Resource
    private PostService postService;

    @Resource
    private TagDao tagDao;

    @Resource
    private PostMessageService postMessageService;

    /**
     * 发表帖子
     * @param post
     * @return
     */
    @PostMapping
    public ResultBean post(@RequestBody Post post){
        return ResultBean.success().setResponseBody(postService.post(post));
    }

    /**
     * 列出我的帖子列表
     * @return
     */
    @GetMapping
    public ResultBean listMy(int page, int pageSize){
        return ResultBean.success().setResponseBody(
                postService.list(Integer.parseInt(ThreadUtil.getUserId().toString()), page, pageSize));
    }

    /**
     * 列出标签列表
     * @return
     */
    @GetMapping("tags")
    public ResultBean tagList(Boolean edit){
        List<Tag> list = null;
        if (edit != null && edit){
            User user = ThreadUtil.getUserEntity();
            list = tagDao.list(Method.where(Tag::getRule, C.XE, user.getRule()));
        }else{
            list = tagDao.list(Method.createDefault());
        }
        return ResultBean.success().setResponseBody(list);
    }

    /**
     * 列出某个用户的帖子
     * @param userId
     * @return
     */
    @GetMapping("{userId}/list")
    public ResultBean list(@PathVariable int userId, int page, int pageSize){
        return ResultBean.success().setResponseBody(
                postService.list(userId, page, pageSize));
    }

    /**
     * 列出广场帖子
     * @param page
     *      页码
     * @param pageSize
     *      每页条数
     * @param lon
     *      我的经度
     * @param lat
     *      我的纬度
     * @param cityName
     *      我所在的城市
     * @return
     */
    @GetMapping("list")
    public ResultBean list(int page, int pageSize, double lon, double lat, String cityName, Boolean top, Integer tagId){
        return ResultBean.success().setResponseBody(
                postService.list(page, pageSize, lon, lat, cityName, null != top && top, null == tagId ? 0 : tagId));
    }

    /**
     * 帖子详情
     * @param id
     *      帖子ID
     * @return
     */
    @GetMapping("info/{id}")
    public ResultBean info(@PathVariable long id, double lon, double lat){
        return ResultBean.success().setResponseBody(postService.info(id, lon, lat));
    }

    /**
     * 删除我自己的一个帖子
     * @param post
     *      要删除的帖子信息
     * @return
     */
    @DeleteMapping
    public ResultBean deleteMy(@RequestBody Post post){
        postService.deleteMy(post);
        return ResultBean.success();
    }

    /**
     * 帖子点赞/取消赞
     * @param id
     *      帖子ID
     * @return
     */
    @PostMapping("{id}/love")
    public ResultBean love(@PathVariable long id){
        return ResultBean.success().putResponseBody("status", postService.love(id));
    }

    /**
     * 未读消息
     * @return
     */
    @GetMapping("message/no-read")
    public ResultBean noReadMessage(){
        return ResultBean.success().setResponseBody(postMessageService.noRead());
    }

    /**
     * 列出消息列表
     * @param type
     *      类型
     * @param page
     *      页码
     * @param pageSize
     *      每页条数
     * @return
     */
    @GetMapping("message/{type}")
    public ResultBean messageList(@PathVariable int type, int page, int pageSize){
        return ResultBean.success().setResponseBody(postMessageService.list(type, page, pageSize));
    }

    /**
     * 使某个类型的消息全部设置为已读
     * @param type
     *      消息类型
     * @return
     */
    @PostMapping("message/{type}")
    public ResultBean read(@PathVariable int type){
        return ResultBean.success().setResponseBody(postMessageService.readAll(type));
    }

    /**
     * 发布评论
     * @param message
     *      评论
     * @return
     */
    @PostMapping("comment")
    public ResultBean postComment(@RequestBody PostMessage message){
        message.setType(CommonAttr.POST_MESSAGE_TYPE.COMMENT);
        return ResultBean.success().setResponseBody(postService.comment(message));
    }

}

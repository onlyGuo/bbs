package com.aiyi.blog.controller.api;

import com.aiyi.blog.entity.Post;
import com.aiyi.blog.service.PostService;
import com.aiyi.core.beans.ResultBean;
import com.aiyi.core.util.thread.ThreadUtil;
import org.springframework.stereotype.Controller;
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
    public ResultBean list(int page, int pageSize, double lon, double lat, String cityName){
        return ResultBean.success().setResponseBody(
                postService.list(page, pageSize, lon, lat, cityName));
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
}

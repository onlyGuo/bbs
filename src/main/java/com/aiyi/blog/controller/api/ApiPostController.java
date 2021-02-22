package com.aiyi.blog.controller.api;

import com.aiyi.blog.entity.Post;
import com.aiyi.blog.service.PostService;
import com.aiyi.core.beans.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * 帖子相关
 */
@Controller("api/v1/post")
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
        return ResultBean.success();
    }


    /**
     * 列出某个用户的帖子
     * @param userId
     * @return
     */
    @GetMapping("{userId}/list")
    public ResultBean list(@PathVariable int userId, int page, int pageSize){
        return ResultBean.success();
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
        return ResultBean.success();
    }

}

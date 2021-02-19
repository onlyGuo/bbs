package com.aiyi.blog.controller.api;

import com.aiyi.blog.entity.ArticleComment;
import com.aiyi.blog.entity.dto.LayerTable;
import com.aiyi.blog.service.ArticleCommentService;
import com.aiyi.core.beans.ResultBean;
import com.aiyi.core.beans.ResultPage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/30 10:18
 * @Email 719348277@qq.com
 * @Description: 评论相关接口
 */
@RestController
@RequestMapping("api/v1/comment")
public class ApiCommentController {

    @Resource
    private ArticleCommentService articleCommentService;

    @GetMapping
    public LayerTable<ArticleComment> list(String keyword, Integer status, int page, int limit){
        ResultPage<ArticleComment> res = articleCommentService.list(keyword, status, page, limit);
        return LayerTable.createByPageResult(res);
    }

    /**
     * 删除评论
     * @param id
     *      评论iD
     * @return
     */
    @DeleteMapping("{id}")
    public ResultBean del(@PathVariable int id){
        articleCommentService.del(id);
        return ResultBean.success();
    }

    /**
     * 更新评论状态(1 = 通过, -1 = 驳回)
     * @param comment
     *      评论信息
     * @return
     */
    @PutMapping("{id}/status")
    public ResultBean updateStatus(@RequestBody ArticleComment comment, @PathVariable int id){
        comment.setId(id);
        articleCommentService.updateStatus(comment);
        return ResultBean.success();
    }

}
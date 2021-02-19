package com.aiyi.blog.controller.api;

import com.aiyi.blog.entity.Classify;
import com.aiyi.blog.entity.dto.LayerTable;
import com.aiyi.blog.service.ClassifyService;
import com.aiyi.core.beans.ResultBean;
import com.aiyi.core.beans.ResultPage;
import com.aiyi.core.exception.ValidationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/15 11:48
 * @Email 719348277@qq.com
 * @Description: 栏目相关接口
 */
@RestController
@RequestMapping("api/v1/classify")
public class ApiClassifyController {

    @Resource
    private ClassifyService classifyService;

    @GetMapping
    public LayerTable<Classify> list(String keyword, Integer status, Integer page, Integer limit){
        if (null == status){
            status = -1;
        }
        if (null == page){
            page = 1;
        }
        if (null == limit){
            limit = 10;
        }
        ResultPage<Classify> list = classifyService.list(keyword, status, page, limit);
        return LayerTable.createByPageResult(list);
    }

    /**
     * 添加分类/栏目
     * @param classify
     *      栏目信息
     * @return
     */
    @PostMapping
    public ResultBean create(@RequestBody Classify classify){
        if (StringUtils.isEmpty(classify)){
            throw new ValidationException("栏目名称不能为空");
        }
        classifyService.create(classify);
        return ResultBean.success();
    }

}
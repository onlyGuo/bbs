package com.aiyi.blog.entity.dto;

import com.aiyi.core.beans.ResultPage;

import java.util.List;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/15 12:10
 * @Email 719348277@qq.com
 * @Description: Layer 表格数据格式
 */
public class LayerTable<T> {

    private int code;

    private long count;

    private List<T> data;

    public static <T> LayerTable<T> createByPageResult(ResultPage<T> page){
        LayerTable<T> table = new LayerTable<>();
        table.code = 0;
        table.count = page.getTotalSize();
        table.data = page.getList();
        return table;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
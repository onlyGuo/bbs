package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.FieldName;
import com.aiyi.core.annotation.po.ID;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.beans.PO;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/31 14:18
 * @Email 719348277@qq.com
 * @Description: 评论过滤规则表
 */
@TableName(name = "blog_rule")
public class Rule extends PO {

    @ID
    private int id;

    /**
     * 过滤关键字, JSON LIST
     */
    private String keyword;

    /**
     * 匹配范围,多选,逗号隔开. 1=昵称,2=邮箱,3=评论内容
     */
    @FieldName(name = "find_range")
    private String findRange;

    /**
     * 匹配规则, 1=所有词汇出现次数总和, 2=出现率最高词汇数, 3=平均每个词汇出现数, 4=全部匹配且按最低出现率的词汇次数, 5=全部匹配且按出现率最高的词汇次数
     */
    private int rule;

    /**
     * 流程处理方法: 1=转人工, 2=自动驳回, 3=自动通过, 4=替换为***并通过
     */
    @FieldName(name = "flow_fun")
    private int flowFun;

    /**
     * 阀值
     */
    private int threshold;

    /**
     * 0=未启用, 1=已启用
     */
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFindRange() {
        return findRange;
    }

    public void setFindRange(String findRange) {
        this.findRange = findRange;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    public int getFlowFun() {
        return flowFun;
    }

    public void setFlowFun(int flowFun) {
        this.flowFun = flowFun;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
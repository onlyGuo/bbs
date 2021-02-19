package com.aiyi.blog.service;

/**
 * 统计相关
 */
public interface StatisticsService {

    /**
     * 周访问数
     * @return
     */
    long weekVisit();

    /**
     * 总访问量
     * @return
     */
    long allVisit();


}

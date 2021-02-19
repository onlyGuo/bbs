package com.aiyi.blog.task;

import com.aiyi.blog.dao.LogMapper;
import com.aiyi.blog.entity.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 访问日志
 */
@Component
public class LogTask {

    public static LinkedList<Log> showLogs = new LinkedList<>();

    @Resource
    private LogMapper logMapper;

    /**
     * 每30秒分钟持久化
     */
    @Scheduled(cron = "0/30 * * * * ? ")
    public void insertToBd(){
        int size = showLogs.size();
        List<Log> sear = new LinkedList<>();
        if (size > 1){
            for (int i = 0; i < size; i++){
                sear.add(showLogs.removeFirst());
            }
            logMapper.insertList(sear);
        }
    }

    /**
     * 记录一次访问
     */
    public void log(){
        Log log = new Log();
        log.setCreateTime(new Date());
        showLogs.addLast(log);
    }

}

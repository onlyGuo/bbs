package com.aiyi.blog.service;

import com.aiyi.blog.entity.PostMessage;
import com.aiyi.blog.entity.dto.PostNoReadMessage;

public interface PostMessageService {

    /**
     * 发送消息
     * @param message
     *      消息本体
     */
    void sendMessage(PostMessage message);

    /**
     * 使某个类型的消息全部已读
     * @param type
     *      消息类型
     */
    void readAll(int type);

    /**
     * 列出所有类型未读消息数
     * @return
     */
    PostNoReadMessage noRead();


}

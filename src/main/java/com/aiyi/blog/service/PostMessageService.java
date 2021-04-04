package com.aiyi.blog.service;

import com.aiyi.blog.entity.PostMessage;
import com.aiyi.blog.entity.dto.PostNoReadMessage;
import com.aiyi.core.beans.ResultPage;

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
    PostNoReadMessage readAll(int type);

    /**
     * 列出所有类型未读消息数
     * @return
     */
    PostNoReadMessage noRead();

    /**
     * 列出指定类型的消息
     * @param type
     *      消息类型
     * @return
     */
    ResultPage<PostMessage> list(int type, int page, int pageSize);


}

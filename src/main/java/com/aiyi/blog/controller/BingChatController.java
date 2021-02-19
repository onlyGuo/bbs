package com.aiyi.blog.controller;

import com.aiyi.blog.assets.NoLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bing")
public class BingChatController {

    @RequestMapping("chat")
    @NoLogin
    public String chat(){
        return "bing-chat";
    }

}

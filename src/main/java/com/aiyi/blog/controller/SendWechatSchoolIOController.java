//package com.aiyi.blog.controller;
//
//import com.aiyi.core.exception.ValidationException;
//import com.aiyi.core.util.MD5;
//import org.springframework.http.HttpStatus;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletResponse;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author: 郭胜凯
// * @Date: 2020/10/9 08:52
// * @Email 719348277@qq.com
// * @Description: 出入校信息微信推送接口
// */
//@RestController
//@RequestMapping("api/v1/wechat/template-msg")
//public class SendWechatSchoolIOController {
//
//    private String wechatAssetsToken = null;
//
//    private long wechatAssetsTokenGetTime = 0;
//
//    private HttpClient client = HttpClients.createDefault();
//
//    private String sessionAssetsKey = "aKIcoOSFOxFn7E0R";
//
//    /**
//     * 推送出入校信息
//     * @return
//     */
//    @PostMapping("inout")
//    public Object sendWechatMsg(@RequestBody Map<String, String> param, HttpServletResponse response){
//        // 验参
//        if (StringUtils.isEmpty(param.get("yitifenAccount")) || null == param.get("action")
//                || StringUtils.isEmpty(param.get("time")) || StringUtils.isEmpty(param.get("url"))){
//            response.setStatus(HttpStatus.BAD_REQUEST.value());
//            return createResult("信息填写有误", null);
//        }
//
//        // 验签
//        ArrayList<String> keys = new ArrayList<>(param.keySet());
//        keys.sort(String::compareTo);
//
//        StringBuilder onSign = new StringBuilder();
//        for (String key: keys){
//            if (!"sign".equals(key)){
//                onSign.append(key).append("=").append(param.get(key)).append("&");
//            }
//        }
//        onSign.append("key=").append(sessionAssetsKey);
//
//        String signed = MD5.getMd5(onSign.toString());
//        if (null == param.get("sign") || !signed.equals(param.get("sign"))){
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            return createResult("验签失败", null);
//        }
//
//        // 查询openId
//        // TODO 这里根据`yitifenAccount`查询数据库中对应用户的openId, 这个数据库操作需要你自己实现
//        String openId = "xxxx";
//        // TODO 这里改成从数据库中查询出来的学生姓名
//        String studentName = "张三";
//        // TODO 这里改成对应的模板ID
//        String templateId = "xxx";
//        String actionStr = "1".equals(param.get("action")) ? "进校" : "离校";
//
//        // 发送模板消息
//        HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + getAssetsToken());
//        post.setEntity(new StringEntity("{\n" +
//                "    \"toUser\": \"" + openId + "\",\n" +
//                "    \"template_id\": \"" + templateId + "\",\n" +
//                "    \"url\": \"" + param.get("url") + "\",\n" +
//                "    \"data\": {\n" +
//                "        \"first\": {\n" +
//                "            \"value\": \"" + studentName + "家长您好, 您的孩子现已" + actionStr + "\"\n" +
//                "        },\n" +
//                "        \"keyword1\": {\n" +
//                "            \"value\": \"" + studentName + "\"\n" +
//                "        },\n" +
//                "        \"keyword2\": {\n" +
//                "            \"value\": \"" + actionStr + "\"\n" +
//                "        },\n" +
//                "        \"keyword3\": {\n" +
//                "            \"value\": \"" + param.get("time") + "\"\n" +
//                "        },\n" +
//                "        \"remark\": {\n" +
//                "            \"value\": \"点击详情可查看历史出入校信息\"\n" +
//                "        }\n" +
//                "    }\n" +
//                "}", StandardCharsets.UTF_8));
//        post.setHeader("content-type","application/json;charset=utf-8");
//        client.execute(post);
//        return "SUCCESS";
//    }
//
//
//    /**
//     * 获取微信token
//     * @return
//     */
//    private String getAssetsToken(){
//        // 两小时(超过一小时59分)后重新获取token
//        if (null == wechatAssetsToken || wechatAssetsTokenGetTime < System.currentTimeMillis() - 1000 * 60 * 60 * 2){
//            HttpGet tockenGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx763acf7a44935e2e&secret=8d0c3c9d653825c2a9b0de72bee34526");
//            HttpResponse execute = client.execute(tockenGet);
//            JSONObject tokenJson = EntityUtil.toString(execute.getEntity(), StandardCharsets.UTF_8);
//            wechatAssetsToken = tokenJson.getString("access_token");
//            wechatAssetsTokenGetTime = System.currentTimeMillis();
//        }
//        return wechatAssetsToken;
//    }
//
//
//    private Map<String, Object> createResult(String msg, Object data){
//        Map<String, Object> result = new HashMap<>();
//        if (StringUtils.isEmpty(msg)){
//            result.put("message", msg);
//        }else{
//            result.put("message", "ok");
//        }
//        if (null != data){
//            result.put("data", data);
//        }
//        return result;
//    }
//
//}
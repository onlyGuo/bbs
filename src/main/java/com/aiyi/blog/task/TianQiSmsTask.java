package com.aiyi.blog.task;

import com.aiyi.blog.util.Festival;
import com.aiyi.blog.util.FestivalUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TianQiSmsTask {

    HttpClient client = HttpClients.createDefault();

    private Logger logger = LoggerFactory.getLogger(TianQiSmsTask.class);

    private static Map<String, String> dayMsgMapper = new HashMap<>();
    static {
        dayMsgMapper.put("", "");
    }


    /**
     * 发送天气短信
     */
    @Scheduled(cron = "0 34 6 * * ? ")
    public void sendSms(){
        logger.debug("开始发送时间短信");
        JSONObject tianQi = getTianQi();

        String city = tianQi.getString("city");
        String wea = tianQi.getString("wea");
        String temMin = tianQi.getString("tem2");
        String temMax = tianQi.getString("tem1");
        String temNow = tianQi.getString("tem");

        String notice = null;
        Festival festival = FestivalUtil.getFestival(new Date());
        if (null != festival){
            notice = festival.getGreetings();
        }
        if (null == notice){
            if (wea.contains("雨")){
                notice = "记得带伞哦";
            }else if (wea.contains("阴")){
                notice = "带上伞，以防万一哦";
            }else if (wea.contains("雪")){
                notice = "记得加衣哦";
            }
        }
        if (null == notice){
            if (Integer.parseInt(temMax) - Integer.parseInt(temMin) > 10){
                notice = "温差较大，不要感冒哦";
            }
        }
        if (null == notice){
            notice = "愿你天天好心情";
        }


        try {
            Credential cred = new Credential("AKID9ApWzIAjnhME6mwaqtgNIB2gATaSjSgX",
                    "iPbcuzrVv1zkIchkfiurmZ7eJXVyBVbS");
            SmsClient client = new SmsClient(cred, "ap-guangzhou");
            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppid("1400461161");
            req.setSign("倚窗听花落");
            /* 模板 ID: 必须填写已审核通过的模板 ID，可登录 [短信控制台] 查看模板 ID */
            String templateID = "825445";
            req.setTemplateID(templateID);
            String[] phoneNumbers = {"+8617744416451", "+8615132161499"};
            req.setPhoneNumberSet(phoneNumbers);

            /* 模板参数: 若无模板参数，则设置为空*/
            String[] templateParams = {"你好", city, wea, temMin, temMax, temNow, notice};
            req.setTemplateParamSet(templateParams);
            SendSmsResponse res = client.SendSms(req);
            System.out.println(SendSmsResponse.toJsonString(res));
            System.out.println(res.getRequestId());
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
    }


    private JSONObject getTianQi(){
        HttpGet get = new HttpGet("https://tianqiapi.com/api?version=v6&appid=61999577&appsecret=hCPDoy9U&city=石家庄");

        try {
            HttpResponse execute = client.execute(get);
            String s = EntityUtils.toString(execute.getEntity(), StandardCharsets.UTF_8);
            System.out.println(s);
            return JSON.parseObject(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new TianQiSmsTask().sendSms();
    }

}

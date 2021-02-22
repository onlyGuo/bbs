package com.aiyi.blog.controller.api;

import com.aiyi.core.beans.ResultBean;
import com.aiyi.core.util.thread.ThreadUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequestMapping("api/v1/file")
public class ApiFileController {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.access-secret}")
    private String accessSecret;

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;

    private OSS oss;

    @PostConstruct
    public void init(){
        oss = new OSSClientBuilder().build("https://" + endpoint, accessKeyId, accessSecret, accessKeyId);
    }


    /**
     * 文件上传
     * @param file
     *      文件对象
     * @return
     */
    @PostMapping("upload")
    public ResultBean upload(@RequestBody MultipartFile file){
        String fileName = ThreadUtil.getUserId() + "/" + UUID.randomUUID().toString().replace("-", "") + ".f";
        try (InputStream in = file.getInputStream()){
            oss.putObject(bucketName, fileName, file.getInputStream());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return ResultBean.success().putResponseBody("url", "https://" + bucketName + "." + endpoint + "/" + fileName);
    }



}

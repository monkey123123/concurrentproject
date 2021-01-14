package me.monkey.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.collection.convert.Wrappers;

import javax.servlet.http.HttpServletRequest;

// service取名sc是方便注解调用
@Service("sc")
@Slf4j
public class SginCheckService {

//    @Autowired
//    private PlatformManageService platformManageService;

    public boolean checkSgin(){
        HttpServletRequest request = UserContextHolder.getHttpServletRequest();
        String appid = request.getHeader("appid");
        String signature = request.getHeader("signature");
        String timestamp = request.getHeader("timestamp");

        if (false) {
//            log.error("签名校验失败:" + "crm:" + lowerCase + "，接口:" + signature);
            //鉴权失败抛出自定义异常
            throw new RuntimeException();
//            throw new SginCheckException();
        }
        //如果鉴权成功不return true 会报错.
        return true;
    }
}
package me.monkey.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.monkey.service.TestService;
import me.monkey.util.SnmpPref;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 */
@Slf4j
@RestController
@Api(tags = "系统:定时任务管理")
@RequestMapping("/api/test")
public class TestController {

    private static final String ENTITY_NAME = "quartzJob";


    @Autowired
    private TestService testService;

    @ApiOperation("查询定时任务")
    @GetMapping
    public ResponseEntity<Object> getJobs(HttpServletRequest request, JobQueryCriteria criteria, Pageable pageable){
//        HTTP Referer是baiheader的一部分，当浏览器向web服务器发送du请求的时候，一般会带上Referer，告诉服zhi务器我是从哪个页面链接dao过来的，服务器籍此可以获得一些信息用于处理。比如从我主页上链接到一个朋友那里，他的服务器就能够从HTTP Referer中统计出每天有多少用户点击我主页上的链接访问他的网站。
//        在JSP中获取REFERER的方式是：
//        Referer: http://topic.csdn.net/u/t5/include/ad1.asp?pdate=2010-07-30%2016:47:51&ba=Java&sa=J2EE
        String referrer = request.getHeader("REFERER");
        System.out.println(SnmpPref.getUser());

        testService.testTransaction();

//        String referrer = request.getHeader("referer");
        testService.run();
        Map<String,Object> map = new HashMap<>(16);
        map.put("test", "value");


        System.out.println("criteria = [" + criteria + "], pageable = [" + pageable + "]");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}

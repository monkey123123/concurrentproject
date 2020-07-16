package me.monkey.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.monkey.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public ResponseEntity<Object> getJobs(JobQueryCriteria criteria, Pageable pageable){
        testService.run();
        Map<String,Object> map = new HashMap<>(16);
        map.put("test", "value");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}

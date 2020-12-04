package me.monkey.dao.controller;

import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("user")
public class UserController {


    @RequestMapping("userChangePassword")
    public Map<String,Object> USER_CHANGE_PASSWORD( String param) throws Exception {
    	System.out.println("come on (data) " + param);

		HashMap<String, Object> stringObjectHashMap = new HashMap<>();
		stringObjectHashMap.put("key","value");
		return stringObjectHashMap;
    }

    

    
}

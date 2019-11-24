package com.beanpodtech.pkms.iot.ota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beanpodtech.pkms.common.message.rabbitmq.RabbitMqCommonMessage;
import com.beanpodtech.pkms.common.message.rabbitmq.RabbitMqResponse;
import com.beanpodtech.pkms.iot.ota.service.OtaService;

@RestController
@RequestMapping("ota")
public class OtaController {

	@Autowired
	private OtaService otaService;
	
	
    @RequestMapping("pushInstallMessage")
    public RabbitMqResponse pushInstallMessage(@RequestBody RabbitMqCommonMessage param) throws Exception {
    	System.out.println("pushInstallMessage come on (data) " + param);
    	RabbitMqResponse res = otaService.pushInstallMessage(param);
    	return res;
    }
    
	
    @RequestMapping("push04")
    public RabbitMqResponse push04(@RequestBody RabbitMqCommonMessage param) throws Exception {
    	System.out.println("push04 come on (data) " + param);
    	RabbitMqResponse res = otaService.push04(param);
    	return res;
    }
    
    
}

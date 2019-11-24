package com.beanpodtech.pkms.iot.ota.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTDevice;

/**
* @author dapeng
* @date 2019-08-12
*/
//@CacheConfig(cacheNames = "device")
public interface IoTDeviceService {

    
    public IoTDevice findById(String id);

    
    public IoTDevice create(IoTDevice resources);

    
    public void update(IoTDevice resources);


	
	public void delete(Long id);

	
	public Object queryAllSemiAutomatic(IoTDevice criteria, Pageable pageable);
	
	
	public List<IoTDevice> queryAllSemiAutomaticNoPage(IoTDevice criteria);
	
	
	public IoTDevice findOne(IoTDevice t);
}
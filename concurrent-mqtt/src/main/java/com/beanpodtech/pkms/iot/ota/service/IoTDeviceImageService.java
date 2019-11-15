package com.beanpodtech.pkms.iot.ota.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTDeviceImage;

/**
* @author dapeng
* @date 2019-08-12
*/
//@CacheConfig(cacheNames = "device")
public interface IoTDeviceImageService {

	
    public IoTDeviceImage findById(String id);

    
    public IoTDeviceImage create(IoTDeviceImage resources);

    
    @Transactional(rollbackFor = Exception.class)
    public void update(IoTDeviceImage resources);

	
	public Object queryAllSemiAutomatic(IoTDeviceImage criteria, Pageable pageable);
	
	
	/*
	 * 不分页
	 */
	
	public List<IoTDeviceImage> queryListNoPage(IoTDeviceImage criteria);
	
	
	public Object queryAllSemiAutomaticComplex(IoTDeviceImage criteria, Pageable pageable);
	
	
	
	/*
	 * 目前4个参数都没用上
	 * @see me.monkey.modules.system.service.IoTDeviceImageService#findList(me.monkey.modules.system.service..mongodb.IoTDeviceImage, org.springframework.data.domain.Pageable, java.lang.String, java.lang.String)
	 */
	
	public List<IoTDeviceImage> findList(IoTDeviceImage criteria, Pageable pageable, LocalDate d1, LocalDate d2);
	
//	
	public List<IoTDeviceImage> findListByDeviceid(IoTDeviceImage criteria);
    
}
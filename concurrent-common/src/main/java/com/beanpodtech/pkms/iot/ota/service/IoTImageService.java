package com.beanpodtech.pkms.iot.ota.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTImage;

/**
* @author dapeng
* @date 2019-08-12
*/
public interface IoTImageService {

	
    public IoTImage findById(String id);

    public IoTImage create(IoTImage resources);

    public void update(IoTImage resources);

	public Object queryAllSemiAutomatic(IoTImage criteria, Pageable pageable);
	
	public List<IoTImage> queryAllSemiAutomaticNoPage(IoTImage criteria);
	public List<IoTImage> queryAll();
	
	public IoTImage findOne(IoTImage t);
}
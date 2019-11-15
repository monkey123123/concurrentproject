/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beanpodtech.pkms.iot.ota.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTDeviceImage;

/**
 *
 */
public interface IoTDeviceImageRepository extends MongoRepository<IoTDeviceImage, String> {
//	Product findOne(String id);
    @Override
    void delete(IoTDeviceImage deleted);
    
//    @Query(value = "{'deviceid': ?0, 'createtime' : {'$gte':?1, '$lt':?2} }" )
//    public List<IoTDeviceImage> findList(String deviceid, String dateStart, String dateEnd);
    
}
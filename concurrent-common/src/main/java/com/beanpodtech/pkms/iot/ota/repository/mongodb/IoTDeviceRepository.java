/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beanpodtech.pkms.iot.ota.repository.mongodb;

import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTDevice;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 */
public interface IoTDeviceRepository extends MongoRepository<IoTDevice, String> {
//	Product findOne(String id);
    @Override
    void delete(IoTDevice deleted);
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beanpodtech.pkms.iot.ota.repository.mongodb;

import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTImage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 */
public interface IoTImageRepository extends MongoRepository<IoTImage, String> {
    @Override
    void delete(IoTImage deleted);
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beanpodtech.pkms.iot.ota.repository.pojo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

/**
 *
 * @author 
 */
@Document(collection = "device_event")
public class IoTDeviceEvent {
    @Id
    String id;

    // 设备id,来自tee
    String deviceid;
    
    @DBRef
    private IoTDevice device;
    @DBRef
    private IoTEvent event;
    // 设备id,来自 collection event
    String eventid;
    // 入库日期
    Date createtime;
    // 事件发生时间
    Date incidenttime;
    // 用户编号
    Long inputuserid;
    // 用户编号
    String inputusername;

    public IoTDeviceEvent() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getIncidenttime() {
		return incidenttime;
	}

	public void setIncidenttime(Date incidenttime) {
		this.incidenttime = incidenttime;
	}

	public Long getInputuserid() {
		return inputuserid;
	}

	public void setInputuserid(Long inputuserid) {
		this.inputuserid = inputuserid;
	}

	public String getInputusername() {
		return inputusername;
	}

	public void setInputusername(String inputusername) {
		this.inputusername = inputusername;
	}
	public void copy(IoTDeviceEvent source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }

}

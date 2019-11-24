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
@Document(collection = "device_image")
public class IoTDeviceImage {
    @Id
    String id;

    // 设备id,来自tee
    String deviceid;
    String imageid;
    String currentimageid;
    
    @DBRef
    private IoTDevice device;
    @DBRef
    private IoTImage image;
    
    // 设备id,来自 collection image
    // 入库日期
    Date createtime;
    //是否有可更新镜像
    private Integer status;
    
    //是否真实在线，离线发送will遗嘱
    Integer online;
    // 用户编号
    Long inputuserid;
    // 用户编号
    String inputusername;

    public IoTDeviceImage() {
    }
	public void copy(IoTDeviceImage source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
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
	public String getImageid() {
		return imageid;
	}
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
	public IoTDevice getDevice() {
		return device;
	}
	public void setDevice(IoTDevice device) {
		this.device = device;
	}
	public IoTImage getImage() {
		return image;
	}
	public void setImage(IoTImage image) {
		this.image = image;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public String getCurrentimageid() {
		return currentimageid;
	}
	public void setCurrentimageid(String currentimageid) {
		this.currentimageid = currentimageid;
	}
	

}

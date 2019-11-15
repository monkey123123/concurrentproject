/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beanpodtech.pkms.iot.ota.repository.pojo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

/**
 *
 */
@Document(collection = "device")
public class IoTDevice {
    @Id
    String id;

    // 设备id,来自tee
    String deviceid;
    // 设备类型
    Integer type;
    // 状态
    Integer status;
    // 用户编号
    Long inputuserid;
    // 用户编号
    String inputusername;
    // 入库日期
    Date createTime;
 // 密钥创建时间
    Date keyCreateTime;
 // 设备最新活动时间
    Date activateTime;
 // dongleid 加解密id
    String factoryCrypterID;
 // 手机厂商id
    String manufacturerID;
 // 芯片厂商id
    String chipManufacturerID;
    // se厂商id
    String seManufacturerID;
    // 
    String ISEEVersion;
    // 设备sn
    String devsn;
    // seSN
    String sesn;
    // 位置
    String location;
    
    public IoTDevice() {
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

	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Long getInputuserid() {
		return inputuserid;
	}


	public void setInputuserid(Long inputuserid) {
		this.inputuserid = inputuserid;
	}


	public Date getKeyCreateTime() {
		return keyCreateTime;
	}


	public void setKeyCreateTime(Date keyCreateTime) {
		this.keyCreateTime = keyCreateTime;
	}


	public Date getActivateTime() {
		return activateTime;
	}


	public void setActivateTime(Date activateTime) {
		this.activateTime = activateTime;
	}


	public String getFactoryCrypterID() {
		return factoryCrypterID;
	}


	public void setFactoryCrypterID(String factoryCrypterID) {
		this.factoryCrypterID = factoryCrypterID;
	}


	public String getManufacturerID() {
		return manufacturerID;
	}


	public void setManufacturerID(String manufacturerID) {
		this.manufacturerID = manufacturerID;
	}


	public String getChipManufacturerID() {
		return chipManufacturerID;
	}


	public void setChipManufacturerID(String chipManufacturerID) {
		this.chipManufacturerID = chipManufacturerID;
	}


	public String getSeManufacturerID() {
		return seManufacturerID;
	}


	public void setSeManufacturerID(String seManufacturerID) {
		this.seManufacturerID = seManufacturerID;
	}


	public String getISEEVersion() {
		return ISEEVersion;
	}


	public void setISEEVersion(String iSEEVersion) {
		ISEEVersion = iSEEVersion;
	}


	public String getDevsn() {
		return devsn;
	}


	public void setDevsn(String devsn) {
		this.devsn = devsn;
	}


	public String getSesn() {
		return sesn;
	}


	public void setSesn(String sesn) {
		this.sesn = sesn;
	}

	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getInputusername() {
		return inputusername;
	}
	
	public void setInputusername(String inputusername) {
		this.inputusername = inputusername;
	}
	public void copy(IoTDevice source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }

    
    
}

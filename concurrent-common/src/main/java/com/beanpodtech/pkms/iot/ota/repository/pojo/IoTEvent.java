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
 * @author didin
 */
@Document(collection = "event")
public class IoTEvent {
    @Id
    String id;

    //模块ID
    String moduleid;
    // 危险等级
    String hazardlevel;
    // 事件描述
    String description;
    // 用户编号
    Long inputuserid;
    // 用户编号
    String inputusername;
    // 入库日期
    Date createtime;

    
    public IoTEvent() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public void copy(IoTEvent source){
		BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
	}

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getHazardlevel() {
		return hazardlevel;
	}

	public void setHazardlevel(String hazardlevel) {
		this.hazardlevel = hazardlevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
    
    
}

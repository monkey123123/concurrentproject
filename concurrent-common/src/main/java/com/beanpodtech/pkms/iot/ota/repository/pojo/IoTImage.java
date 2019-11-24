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
@Document(collection = "image")
public class IoTImage {
    @Id
    String id;

    String imagename;
    String imageversion;//版本
    String imagefile;//签名镜像
    String imagefilepath;//签名镜像
    String description;//
    String imagerawfile;//原始镜像
    String param;//
    String remark;//
    
    // 用户编号
    Long inputuserid;
    // 用户编号
    String inputusername;
    // 入库日期
    Date createTime;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getImageversion() {
		return imageversion;
	}
	public void setImageversion(String imageversion) {
		this.imageversion = imageversion;
	}
	public String getImagefile() {
		return imagefile;
	}
	public void setImagefile(String imagefile) {
		this.imagefile = imagefile;
	}
	public String getImagerawfile() {
		return imagerawfile;
	}
	public void setImagerawfile(String imagerawfile) {
		this.imagerawfile = imagerawfile;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagefilepath() {
		return imagefilepath;
	}
	public void setImagefilepath(String imagefilepath) {
		this.imagefilepath = imagefilepath;
	}
	public void copy(IoTImage source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
 
}

package me.monkey.dao.keyimport;


import com.beanpodtech.pkms.common.model.keyimport.DeviceKey;

public interface DeviceKeyMapper {
    int deleteByPrimaryKey(String devSn);

    int insert(DeviceKey record);

    int insertSelective(DeviceKey record);

    DeviceKey selectByPrimaryKey(String devSn);

    int updateByPrimaryKeySelective(DeviceKey record);

    int updateByPrimaryKey(DeviceKey record);
}
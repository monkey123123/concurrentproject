package me.monkey.dao;

import com.beanpodtech.pkms.common.model.tam.DeviceInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface IDeviceInfoDao {

    public Integer insertDeviceInfo(DeviceInfo deviceInfo);

    public void updateDeviceInfo(DeviceInfo deviceInfo);

    public List<DeviceInfo> selectDeviceInfo(HashMap param);
}

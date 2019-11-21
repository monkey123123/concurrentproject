package me.monkey.dao;

import com.beanpodtech.pkms.common.model.tam.TeeCapabilityInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface ITeeCapabilityInfoDao {

    public Integer insertTeeCapabilityInfo(TeeCapabilityInfo teeCapabilityInfo);

    public void updateByMap(HashMap param);
    public void deleteLogic(HashMap param);
    public void updateTeeCapabilityInfo(TeeCapabilityInfo teeCapabilityInfo);

    public List<TeeCapabilityInfo> selectById(TeeCapabilityInfo teeCapabilityInfo);

    public int selectCountByMap(HashMap param);
    public List<TeeCapabilityInfo> selectByMap(HashMap param);

}

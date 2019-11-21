package me.monkey.dao;

import com.beanpodtech.pkms.common.model.tam.SpInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface ISpInfoDao {

    public Integer insertSpInfo(SpInfo spInfo);

    public void updateSpInfo(SpInfo spInfo);
    public void updateByMap(HashMap param);
    public void deleteLogic(HashMap param);

    public SpInfo selectById(SpInfo spInfo);
    public int selectCountByMap(HashMap param);
    public List<SpInfo> selectByMap(HashMap param);
}

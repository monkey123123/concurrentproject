package me.monkey.dao;

import com.beanpodtech.pkms.common.model.tam.SdInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface ISdInfoDao {
    public Integer insertSdInfo(SdInfo sdInfo);

    public void updateSdInfo(SdInfo sdInfo);
    public void updateByMap(HashMap param);
    public void deleteLogic(HashMap param);

    public SdInfo selectById(SdInfo sdInfo);

    public int selectCountByMap(HashMap param);
    public List<SdInfo> selectByMap(HashMap param);
}

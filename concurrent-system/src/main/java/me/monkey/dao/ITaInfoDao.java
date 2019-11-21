package me.monkey.dao;

import com.beanpodtech.pkms.common.model.tam.TaInfo;
import com.beanpodtech.pkms.common.model.tam.TaInfoVO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface ITaInfoDao {

    public Integer insertTaInfo(TaInfo taInfo);

    public void updateTaInfo(TaInfo taInfo);

    //复杂条件修改
    public void updateTaByMap(HashMap param);
    public void updateTaDownloadByMap(HashMap param);
    public void deleteLogic(HashMap param);

    public TaInfo selectTaInfo(TaInfo taInfo);
    public List<TaInfo> selectTaInfoByMap(HashMap param);

//以下两个方法要配合使用
    public int selectCountByMap(HashMap param);
    public List<TaInfoVO> selectByMap(HashMap param);
}

package me.monkey.dao;

import com.beanpodtech.pkms.common.model.tam.SubTeeInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 * TEE目前一共只有3种 暂时不做增删改查也可以
 */
public interface ITeeInfoDao {

    public Integer insertSubTeeInfo(SubTeeInfo subTeeInfo);
    public void updateSubTeeInfo(SubTeeInfo subTeeInfo);
    public void updateByMap(HashMap param);
    public void deleteLogic(HashMap param);

    public SubTeeInfo selectById(SubTeeInfo subTeeInfo);

    public int selectCountByMap(HashMap param);
    public List<SubTeeInfo> selectByMap(HashMap param);

}

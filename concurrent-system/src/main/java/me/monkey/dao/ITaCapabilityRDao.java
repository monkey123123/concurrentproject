package me.monkey.dao;

import com.beanpodtech.pkms.common.model.tam.TaCapabilityR;

import java.util.HashMap;
import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface ITaCapabilityRDao {

    public Integer insertTaCapabilityR(TaCapabilityR taCapability);

    public void updateTaCapabilityR(TaCapabilityR taCapability);
    public void deleteTaCapabilityRLogic(TaCapabilityR taCapability);
    public void deleteTaCapabilityR(TaCapabilityR taCapability);
    public void updateByMap(HashMap param);

    public List<TaCapabilityR> selectByMap(HashMap param);

    public List<TaCapabilityR> selectById(TaCapabilityR taCapabilityR);
}

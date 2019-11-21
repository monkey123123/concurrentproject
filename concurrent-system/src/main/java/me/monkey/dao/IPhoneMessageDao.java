package me.monkey.dao;

import com.beanpodtech.pkms.common.model.Message;
import com.beanpodtech.pkms.common.model.PhoneMessage;

import java.util.HashMap;
import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface IPhoneMessageDao {

    public void insertPhoneMessage(PhoneMessage phoneMessage);

    public void updatePhoneMessage(PhoneMessage phoneMessage);

    public void updatePhoneMessageByMap(HashMap param);

    public List<PhoneMessage> selectPhoneMessage(HashMap param);
}

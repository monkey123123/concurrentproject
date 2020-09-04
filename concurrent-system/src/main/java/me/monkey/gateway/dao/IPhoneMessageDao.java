package me.monkey.gateway.dao;


import java.util.HashMap;
import java.util.List;

import me.monkey.gateway.pojo.PhoneMessage;

/**
 */
public interface IPhoneMessageDao {

    public void insertPhoneMessage(PhoneMessage phoneMessage);

    public void updatePhoneMessage(PhoneMessage phoneMessage);

    public void updatePhoneMessageByMap(HashMap param);

    public List<PhoneMessage> selectPhoneMessage(HashMap param);
}

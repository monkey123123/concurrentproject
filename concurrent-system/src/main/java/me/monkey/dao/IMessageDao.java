package me.monkey.dao;

import com.beanpodtech.pkms.common.model.Message;

import java.util.HashMap;
import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface IMessageDao {

    public void insertMessage(Message message);

    public void updateMessage(Message message);

    public List<Message> selectMessage(HashMap param);
}

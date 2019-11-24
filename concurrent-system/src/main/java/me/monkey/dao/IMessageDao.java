package me.monkey.dao;

import java.util.HashMap;
import java.util.List;

import me.monkey.pojo.Message;

/**
 */
public interface IMessageDao {

    public void insertMessage(Message message);

    public void updateMessage(Message message);

    public List<Message> selectMessage(HashMap param);
}

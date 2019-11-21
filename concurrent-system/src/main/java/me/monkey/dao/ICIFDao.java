package me.monkey.dao;

import com.beanpodtech.pkms.common.model.CifShareSecret;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface ICIFDao {

    public void insertCifShareSecret(CifShareSecret cifShareSecret);

    public void deleteCifShareSecret(String transactionId);

    public String selectShareSecret(String transactionId);
}

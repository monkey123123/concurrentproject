package me.monkey.dao;

import com.beanpodtech.pkms.common.model.BankUserInfo;
import com.beanpodtech.pkms.common.model.SmsReqRecode;
import com.beanpodtech.pkms.common.model.SmsRespRecode;

public interface ISMSDao {
    //查询银行用户
    public BankUserInfo getBankUser(BankUserInfo userInfo);
    //保存银行用户
    public void saveBankUser(BankUserInfo userInfo);
    //保存银行用户请求记录
    public void saveReqRecode(SmsReqRecode reqRecode);
    //保存银行用户响应记录
    public void saveRespRecode(SmsRespRecode respRecode);
}

package me.monkey.dao;

import com.beanpodtech.pkms.common.model.*;
import org.apache.ibatis.annotations.Param;
import sun.misc.Request;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Bob Liu on 18-4-8.
 */
public interface IOTrPTsmDao {

    void insertTransactionTaskInfo(TransactionTaskInfo transactionTaskInfo);

    TransactionTaskInfo selectTransactionTaskInfoByTid(String tid);

    void updateTransactionTaskInfoById(TransactionTaskInfo transactionTaskInfo);

    void insertRequestTaskInfo(RequestTaskInfo requestTaskInfo);

    List<RequestTaskInfo> selectRequestTaskInfoByTid(Integer tid);

    void updateRequestTaskInfoById(RequestTaskInfo requestTaskInfo);
}

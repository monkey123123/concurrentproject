package me.monkey.dao;

import com.beanpodtech.pkms.common.model.LogInfo;
import com.beanpodtech.pkms.common.model.ParamInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by renzhibin on 17-8-17.
 */
public interface ILogDao {

    public List<LogInfo> selectLogInfo(Map param);

    public void insertLogInfo(LogInfo logInfo);

    public List<ParamInfo> selectParamInfoByLogTraceId(int logTraceId);
}

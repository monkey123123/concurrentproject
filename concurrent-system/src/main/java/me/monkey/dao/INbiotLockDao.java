package me.monkey.dao;

import com.beanpodtech.pkms.common.model.nbiot.NbiotLock;
import com.beanpodtech.pkms.common.model.nbiot.NbiotLockUser;
import com.beanpodtech.pkms.common.param.nbiot.UserRegistDeviceSaveParam;

public interface INbiotLockDao {
    /**
     * 锁信息查询
     * @param nbiotLock
     * @return
     */
    public NbiotLock lockInfoQuery(NbiotLock nbiotLock);

    /**
     * 锁信息保存
     * @param lockInfo
     */
    public void userLockInfoSave(NbiotLock lockInfo);

    /**
     * 锁信息更新
     * @param lockInfo
     */
    public void userLockInfoUpdate(NbiotLock lockInfo);

    /**
     * 用户注册信息保存
     * @param registDeviceInfo
     */
    public void userRegistDeviceInfoSave(NbiotLockUser registDeviceInfo);

    /**
     * 用户注册信息查询
     * @param phone
     * @return
     */
    public NbiotLockUser UserInfoQuery(String phone);

    /**
     * 更新用户注册信息
     * @param registDeviceInfo
     * @return
     */
    public int updateUserInfo(NbiotLockUser registDeviceInfo);
}

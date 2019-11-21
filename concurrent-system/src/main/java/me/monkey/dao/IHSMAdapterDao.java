package me.monkey.dao;

import com.beanpodtech.pkms.common.model.DongleInfo;
import com.beanpodtech.pkms.common.model.HSMEncryptedKey;

import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface IHSMAdapterDao {

    public List<HSMEncryptedKey> selectHsmEncryptedKeyById(HSMEncryptedKey hsmEncryptedKey);

    public void insertDongleInfo(DongleInfo dongleInfo);

    public String selectDonglePublicKey(String dongleId);

    public void updateDongleStatus(DongleInfo dongleInfo);

    public void deleteDongle(DongleInfo dongleInfo);
}

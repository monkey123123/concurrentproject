package me.monkey.dao;

import com.beanpodtech.pkms.common.model.SignatureReq;
import com.beanpodtech.pkms.common.model.SignatureReqDetailsInfo;

import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface ISignatureDao {

    public void insertSignatureReq(SignatureReq signatureReq);

    public List<SignatureReqDetailsInfo> selectSignatureReq(SignatureReq signatureReq);

    public void updateSignatureReq(SignatureReq signatureReq);
}

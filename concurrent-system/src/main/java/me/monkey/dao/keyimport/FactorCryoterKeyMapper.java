package me.monkey.dao.keyimport;


import com.beanpodtech.pkms.common.model.keyimport.FactorCryoterKey;

public interface FactorCryoterKeyMapper {
    int deleteByPrimaryKey(String factorCryoterId);

    int insert(FactorCryoterKey record);

    int insertSelective(FactorCryoterKey record);

    FactorCryoterKey selectByPrimaryKey(String factorCryoterId);

    int updateByPrimaryKeySelective(FactorCryoterKey record);

    int updateByPrimaryKey(FactorCryoterKey record);
}
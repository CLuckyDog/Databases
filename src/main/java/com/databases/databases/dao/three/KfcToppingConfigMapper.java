package com.databases.databases.dao.three;

import com.databases.databases.domain.three.KfcToppingConfig;

public interface KfcToppingConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KfcToppingConfig record);

    int insertSelective(KfcToppingConfig record);

    KfcToppingConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KfcToppingConfig record);

    int updateByPrimaryKey(KfcToppingConfig record);
}
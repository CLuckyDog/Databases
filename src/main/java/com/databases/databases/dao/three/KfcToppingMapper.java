package com.databases.databases.dao.three;

import com.databases.databases.domain.three.KfcTopping;

public interface KfcToppingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KfcTopping record);

    int insertSelective(KfcTopping record);

    KfcTopping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KfcTopping record);

    int updateByPrimaryKey(KfcTopping record);
}
package com.databases.databases.dao.one;

import com.databases.databases.domain.one.TUsers;

import java.util.List;

public interface TUsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUsers record);

    int insertSelective(TUsers record);

    TUsers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUsers record);

    int updateByPrimaryKey(TUsers record);

}
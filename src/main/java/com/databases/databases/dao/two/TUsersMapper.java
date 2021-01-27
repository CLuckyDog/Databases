package com.databases.databases.dao.two;

import com.databases.databases.domain.two.TUsers;

public interface TUsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUsers record);

    int insertSelective(TUsers record);

    TUsers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUsers record);

    int updateByPrimaryKey(TUsers record);
}
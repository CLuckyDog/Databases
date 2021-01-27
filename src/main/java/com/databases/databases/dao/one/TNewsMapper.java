package com.databases.databases.dao.one;

import com.databases.databases.domain.one.TNews;

public interface TNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TNews record);

    int insertSelective(TNews record);

    TNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TNews record);

    int updateByPrimaryKey(TNews record);
}
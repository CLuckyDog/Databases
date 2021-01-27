package com.databases.databases.dao.two;

import com.databases.databases.domain.two.TNews;

public interface TNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TNews record);

    int insertSelective(TNews record);

    TNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TNews record);

    int updateByPrimaryKey(TNews record);
}
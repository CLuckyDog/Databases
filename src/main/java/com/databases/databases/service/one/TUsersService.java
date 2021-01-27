package com.databases.databases.service.one;

import com.databases.databases.domain.one.TUsers;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TUsersService {
    List<TUsers> list();
    PageInfo<TUsers> page();
}

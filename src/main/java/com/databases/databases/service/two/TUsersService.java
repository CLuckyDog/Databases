package com.databases.databases.service.two;

import com.databases.databases.domain.two.TUsers;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TUsersService {
    List<TUsers> list();
    PageInfo<TUsers> page();
}

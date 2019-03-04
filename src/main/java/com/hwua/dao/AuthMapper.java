package com.hwua.dao;

import com.hwua.po.Sys_Auth;

import java.util.List;
import java.util.Map;

public interface AuthMapper {

    //查询权限个数
    Integer countAuth();

    //查询所有权限
    List<Map<String,Object>> getAllAuths();

    //添加权限
    Integer addAuth(Sys_Auth auth);

    //编辑权限
    Integer editAuth(Sys_Auth auth);
}

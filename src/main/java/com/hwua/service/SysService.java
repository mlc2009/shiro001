package com.hwua.service;

import com.hwua.po.Sys_Auth;
import com.hwua.po.Sys_Role;
import com.hwua.po.Sys_User;

import java.util.List;
import java.util.Map;

public interface SysService {

    //用户登录
    Sys_User findUserByName(String username);

    //查询菜单
    Map<Sys_Auth,List<Sys_Auth>> getAllAuths();

    //查询当前用户具有的所有角色
    List<Sys_Role> getAllRolesByUser(String username);

    //查询当前用户具有的所有权限
    List<Sys_Auth> getAllAuthsByUser(String username);
}


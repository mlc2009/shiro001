package com.hwua.service;

import com.alibaba.fastjson.JSONObject;
import com.hwua.po.Sys_Auth;

public interface AuthService {

    //查询所有权限
    JSONObject getAllAuths();

    //添加或修改权限
    boolean addOrUpdateAuth(Sys_Auth auth);
}

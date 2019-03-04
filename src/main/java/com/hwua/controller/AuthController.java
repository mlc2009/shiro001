package com.hwua.controller;

import com.alibaba.fastjson.JSONObject;
import com.hwua.po.Sys_Auth;
import com.hwua.service.AuthService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    //查询权限列表
    @RequestMapping(value = "/getJsonAuths",produces = "text/html;chatset=utf-8")
    @ResponseBody
    public String getJsonAuths(){
        JSONObject object = authService.getAllAuths();
        return object.toJSONString();
    }

    @RequiresPermissions("编辑权限")
    @RequestMapping(value = "/addOrUpdateAuth",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addOrUpdateAuth(Sys_Auth auth){
        System.out.println(auth);
        boolean b = authService.addOrUpdateAuth(auth);
        return b?"ok":"error";
    }


}

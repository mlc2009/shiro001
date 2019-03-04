package com.hwua.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequiresPermissions("权限管理")
    @RequestMapping("/auth/list")
    public String authList(){
        return "admin-auth";
    }

    @RequestMapping("/role/list")
    public String roleList(){
        return "admin-role";
    }

    @RequestMapping("/user/list")
    public String userList(){
        return "admin-user";
    }

    @RequestMapping("/admin-edit")
    public String adminEdit(){
        return "admin-edit";
    }
    @RequestMapping("/admin-authedit")
    public String authEdit(){
        return "admin-authedit";
    }
}

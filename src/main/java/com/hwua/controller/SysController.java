package com.hwua.controller;

import com.hwua.po.Sys_Auth;
import com.hwua.po.Sys_User;
import com.hwua.service.SysService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class SysController {
    @Autowired
    private SysService sysService;

    @RequestMapping("/userLogin")
    public ModelAndView userLogin(String username, String password, HttpSession session){
        ModelAndView mv = new ModelAndView("forward:/toIndex");

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            session.setAttribute("username",username);
        }catch (UnknownAccountException e){
            mv.addObject("msg","账户不存在");
            mv.addObject("preName",username);
            mv.setViewName("login");
        }catch (IncorrectCredentialsException e){
            mv.addObject("msg","密码不对");
            mv.addObject("preName",username);
            mv.setViewName("login");
        }

        return mv;
    }

    @RequestMapping("/toIndex")
    public ModelAndView toIndex(){
        ModelAndView mv = new ModelAndView("index");
        Map<Sys_Auth, List<Sys_Auth>> listMap = sysService.getAllAuths();
        mv.addObject("auths",listMap);
        return mv;
    }

    @RequestMapping("/userLogout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


}

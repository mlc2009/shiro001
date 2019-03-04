package com.hwua.config;

import com.hwua.po.Sys_Auth;
import com.hwua.po.Sys_Role;
import com.hwua.po.Sys_User;
import com.hwua.service.SysService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysService sysService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        //根据用户名，去查询用户信息，得到用户信息以后，可能会得到一个User对象。
        Sys_User user = sysService.findUserByName(username);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Sys_User user = (Sys_User) principalCollection.getPrimaryPrincipal();
        List<Sys_Role> roles = sysService.getAllRolesByUser(user.getUsername());
        Set<String> allRoleNames = new HashSet<>();
        for (Sys_Role role : roles) {
            allRoleNames.add(role.getRole_name());
        }

        List<Sys_Auth> auths = sysService.getAllAuthsByUser(user.getUsername());

        Set<String> allAuthNames = new HashSet<>();
        for (Sys_Auth a : auths) {
            allAuthNames.add(a.getAuth_name());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(allRoleNames);
        info.addStringPermissions(allAuthNames);

        return info;
    }
}

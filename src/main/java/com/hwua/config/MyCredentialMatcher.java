package com.hwua.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Component
public class MyCredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //用户输入的
        String pwd1 = new String(token.getPassword());
        String username = token.getUsername();
        Md5Hash hash = new Md5Hash(pwd1,username,3);
        String pwd11 = hash.toString(); // 之后之后的密码

        //从数据库根据用户名取出的密码
        Object pwd2 = authenticationInfo.getCredentials();

        /*System.out.println("原始密码:  "+pwd1);
        System.out.println("数据库取出的: "+pwd2);*/
        return this.equals(pwd11,pwd2);
    }

    public static void main(String[] args) {
        Md5Hash hash = new Md5Hash("123","ls",3);
        String s = hash.toString();
        System.out.println(s);
    }

}

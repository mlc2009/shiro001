package com.hwua.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(org.apache.shiro.authz.AuthorizationException.class)
    public String nopermission(){
        return "nopermission";
    }

}

package com.hwua.shiro001;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hwua")
@MapperScan("com.hwua.dao")
public class Shiro001Application {

	public static void main(String[] args) {
		SpringApplication.run(Shiro001Application.class, args);
	}

}


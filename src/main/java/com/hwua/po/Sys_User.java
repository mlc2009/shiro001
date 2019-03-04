package com.hwua.po;

import lombok.*;
import java.util.Date;

@Setter @Getter @ToString @NoArgsConstructor@AllArgsConstructor
public class Sys_User {
    private Integer user_id;
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String email;
    private String phone;
    private Integer dept_id;
    private Date register_date;
    private Integer is_valid;
}

package com.myblog3.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOremail;
    private String password;

}

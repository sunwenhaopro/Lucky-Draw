package com.example.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterCmd extends Command {
    @NotNull(message = "账号不为空")
    @NotEmpty(message = "账号不为空")
    private String username;
    @NotEmpty(message = "密码不为空")
    @NotNull(message = "密码不为空")
    private String password;
    @NotEmpty(message = "姓名不为空")
    @NotNull(message = "姓名不为空")
    private String name;
    @Email(message = "邮箱格式不对")
    @NotEmpty(message = "mail不为空")
    @NotNull(message = "mail不为空")
    private String mail;

}

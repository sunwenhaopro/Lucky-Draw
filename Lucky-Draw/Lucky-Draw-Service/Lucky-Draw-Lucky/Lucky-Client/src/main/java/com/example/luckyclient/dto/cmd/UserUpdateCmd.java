package com.example.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class UserUpdateCmd extends Command {

    private Long id;
    private String name;
    private String sex;
    private String address;
    private String phone;
    private String birth;
    private String avatar;

}

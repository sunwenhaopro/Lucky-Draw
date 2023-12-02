package com.example.luckyinfrastructure.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName(value ="bld_user")
@Data
public class UserDB implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String mail;

    private String sex;

    private String phone;
    private String birth;
    private String avatar;
    private String address;
    private String github;
    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;

    /**
     *
     */


    /**
     *
     */
    private LocalDateTime updateTime;

    /**
     *
     */


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
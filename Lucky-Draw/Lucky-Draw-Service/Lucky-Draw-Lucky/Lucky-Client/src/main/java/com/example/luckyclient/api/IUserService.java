package com.example.luckyclient.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyclient.dto.data.TokenVO;
import com.example.luckyclient.dto.data.UserVO;
import com.example.luckyclient.dto.query.UserListByParamQuery;
import com.example.luckyclient.dto.query.UserLoginQuery;
import com.example.luckyclient.dto.cmd.UserRegisterCmd;
import com.example.luckyclient.dto.cmd.UserUpdateCmd;

public interface IUserService {
    Boolean register(UserRegisterCmd cmd);

    TokenVO login(UserLoginQuery query);

    IPage<UserVO> page(UserListByParamQuery query);

    UserVO one(Long id);

    Boolean updateById(UserUpdateCmd cmd);

    String registerVerify(String verifuCode);

    TokenVO refresh();

    TokenVO verifyGithub(String code);
}

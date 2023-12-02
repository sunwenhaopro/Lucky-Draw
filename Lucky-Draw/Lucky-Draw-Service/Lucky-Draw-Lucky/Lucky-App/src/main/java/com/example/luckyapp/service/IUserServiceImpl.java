package com.example.luckyapp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.example.luckyapp.user.command.*;
import com.example.luckyapp.user.query.UserListByParamQueryExe;
import com.example.luckyapp.user.query.UserLoginQueryExe;
import com.example.luckyclient.api.IUserService;
import com.example.luckyclient.dto.cmd.UserUpdateCmd;
import com.example.luckyclient.dto.data.TokenVO;
import com.example.luckyclient.dto.data.UserVO;
import com.example.luckyclient.dto.query.UserListByParamQuery;
import com.example.luckyclient.dto.query.UserLoginQuery;
import com.example.luckyclient.dto.cmd.UserRegisterCmd;

import com.example.luckydrawconfig.exception.Exception;
import com.example.luckydrawconfig.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;


@Slf4j
@Service
@AllArgsConstructor
public class IUserServiceImpl implements IUserService {
    private final UserRegisterCmdExe userRegisterCmdExe;
    private final UserLoginQueryExe userLoginQueryExe;
    private final UserListByParamQueryExe userListByParamQueryExe;
    private final UserUpdateCmdExe userUpdateCmdExe;
    private final UserRegisterVerifyExe userRegisterVerifyExe;
    private final UserRefreshTokenExe userRefreshTokenExe;
    private final UserVerifyGithubExe userVerifyGithubExe;
    @Override
    public Boolean register(UserRegisterCmd cmd) {
         return userRegisterCmdExe.execute(cmd);
    }

    @Override
    public TokenVO login(UserLoginQuery query) {
        UserVO execute =userLoginQueryExe.execute(query);
        return createToken(execute);
    }

    @Override
    public IPage<UserVO> page(UserListByParamQuery query) {
        return userListByParamQueryExe.execute(query);
    }

    @Override
    public UserVO one(Long id) {
        UserListByParamQuery query=new UserListByParamQuery();
        query.setId(id);
        IPage<UserVO> userVOIPage = userListByParamQueryExe.execute(query);
        if(CollectionUtils.isEmpty(userVOIPage.getRecords()))
        {
            throw  new Exception("用户不存在");
        }
        return userVOIPage.getRecords().get(0);
    }

    @Override
    public Boolean updateById(UserUpdateCmd cmd) {
        return userUpdateCmdExe.execute(cmd);
    }

    @Override
    public String registerVerify(String verifyCode) {
        return userRegisterVerifyExe.execute(verifyCode);
    }

    @Override
    public TokenVO  refresh() {
        UserVO userVO =userRefreshTokenExe.execute();
       return createToken(userVO);
    }

    @Override
    public TokenVO verifyGithub(String code) {
       UserVO userVO= userVerifyGithubExe.execute(code);
       return createToken(userVO);
    }

    public TokenVO createToken(UserVO userVO)
    {
        Map<String,String> map1=Map.of("username", userVO.getUsername(),"name", userVO.getName(),"id",userVO.getId().toString(),"accessToken","true");
        Map<String,String> map2=Map.of("username", userVO.getUsername(),"id",userVO.getId().toString(),"refreshToken","true");
        String access_token= JwtUtil.createToken(map1,30*60*1000L);
        String refresh_token= JwtUtil.createToken(map2,7*24*60*60*1000L);
        return new TokenVO(access_token,refresh_token);
    }
}

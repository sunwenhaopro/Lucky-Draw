package com.example.luckyadapter.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.LuckyDrawCommon.annotation.ResponseResult;
import com.example.luckyclient.api.IUserService;
import com.example.luckyclient.dto.cmd.UserUpdateCmd;
import com.example.luckyclient.dto.data.TokenVO;
import com.example.luckyclient.dto.data.UserVO;
import com.example.luckyclient.dto.query.UserListByParamQuery;
import com.example.luckyclient.dto.query.UserLoginQuery;
import com.example.luckyclient.dto.cmd.UserRegisterCmd;
import com.example.luckydrawconfig.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@Slf4j
@ResponseResult
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
    private final IUserService userService;
    @PostMapping("/register")
    public Boolean register(@Validated @RequestBody UserRegisterCmd cmd) {
        return userService.register(cmd);
    }

    @GetMapping("/register/{verifyCode}")
    public String registerVerify(@PathVariable("verifyCode") String verifyCode)
    {
        return userService.registerVerify(verifyCode);
    }

    @PostMapping("/login")
    public TokenVO login(@Validated @RequestBody UserLoginQuery query) {
        return userService.login(query);
    }
    @GetMapping("/refresh")
    public TokenVO refreshToken()
    {
        return  userService.refresh();
    }

    @GetMapping("/me")
    public UserVO me() {
        return userService.one(SecurityUtil.getUserId());
    }

    @PostMapping("/page")
    public IPage<UserVO> page(@Validated @RequestBody UserListByParamQuery query){
        return userService.page(query);
   }

    @GetMapping("/one")
    public UserVO one(@RequestParam("id") Long id){
        return userService.one(id);
    }

    @PostMapping("/update")
    public Boolean update(@RequestBody UserUpdateCmd cmd){
        cmd.setId(SecurityUtil.getUserId());
        return userService.updateById(cmd);
    }
    @GetMapping("/oauth/github")
    public TokenVO verifyGithub(@PathParam("code")String code )
    {
        return userService.verifyGithub(code);
    }
}

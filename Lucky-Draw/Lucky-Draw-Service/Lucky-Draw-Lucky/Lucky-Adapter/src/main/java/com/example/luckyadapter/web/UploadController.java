package com.example.luckyadapter.web;

import com.example.LuckyDrawCommon.annotation.ResponseResult;
import com.example.LuckyDrawCommon.config.FastDFSConfig;
import com.example.luckyclient.api.IUserService;
import com.example.luckyclient.dto.cmd.UserUpdateCmd;
import com.example.luckydrawconfig.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@Slf4j
@ResponseResult
@RequestMapping("/v1/upload")
public class UploadController {
    @Resource
    private  IUserService userService;

    @Value("${fastdfs.nginx.host}")
    private  String nginxHost;

    @PostMapping("/uploadActivitySurface")
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        String fileId = FastDFSConfig.upload(multipartFile);
        return nginxHost+fileId;
    }
    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("file") MultipartFile multipartFile) {
        String fileId = FastDFSConfig.upload(multipartFile);
        UserUpdateCmd cmd=new UserUpdateCmd();
        cmd.setId(SecurityUtil.getUserId());
        cmd.setAvatar(nginxHost+fileId);
        userService.updateById(cmd);
        return nginxHost+fileId;
    }
    @PostMapping("/uploadAwardPhoto")
    public String uploadAwardPhoto(@RequestParam("file") MultipartFile multipartFile) {
        String fileId = FastDFSConfig.upload(multipartFile);
        UserUpdateCmd cmd=new UserUpdateCmd();
        cmd.setId(SecurityUtil.getUserId());
        cmd.setAvatar(nginxHost+fileId);
        userService.updateById(cmd);
        return nginxHost+fileId;
    }
}

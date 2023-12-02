package com.example.luckyclient.dto.data;

import lombok.Data;

@Data
public class TokenVO {
    private String access_token;
    private String refresh_token;
    public TokenVO(String access_token, String refresh_token)
    {
        this.access_token=access_token;
        this.refresh_token=refresh_token;
    }
}

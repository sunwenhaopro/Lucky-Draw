package com.example.luckydrawconfig.util;

import com.example.luckydrawconfig.exception.Exception;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

public class AccessGithubToken {
    private static final String CLIENT_ID= "5652a12b49997d1edc5d";
    private static final String CLIENT_SECRET= "418c25921fd11bea6b875023ef33c0ac0acc930e";
    private static final String  ACCESS_TOKEN_URL="https://github.com/login/oauth/access_token";
    private static final String  USER_INFO= "https://api.github.com/user";
    private static final  WebClient webClient =WebClient.builder().build();

    public static String  getUserInfo  (String code)
    {
        try {
            String url = ACCESS_TOKEN_URL + '?' + "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=" + code;
            Mono<Map> accessMono = webClient.post().uri(url).header("Accept", "application/json").retrieve().bodyToMono(Map.class);
            Mono<Map> userInfoMono = webClient.get().uri(USER_INFO).header("Authorization", "Bearer" + " " + accessMono.block().get("access_token").toString()).retrieve().bodyToMono(Map.class);
            return userInfoMono.block().get("login").toString();
        }catch (java.lang.Exception e)
        {
            throw new Exception("认证失败或超时！请稍后重试");

        }
    }

}

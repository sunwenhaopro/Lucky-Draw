package com.example.luckydrawconfig.util;

import cn.hutool.core.date.DateTime;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {
    private static final String SIGNATURE = "sunwenhao";


    public static String createToken(Map<String,String> map,Long expireTime){
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        System.out.println(map);
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        builder.withHeader(header);
        builder.withExpiresAt(new Date(System.currentTimeMillis()+expireTime)) ;
        builder.withIssuedAt(new Date());
        return builder.sign(Algorithm.HMAC256(SIGNATURE));
    }

    public static Map<String, String> verifyToken(String token) throws Exception {
        DecodedJWT jwt = null;
        Map<String, String> result = new HashMap<>();
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SIGNATURE)).build();
            jwt = verifier.verify(token);
            jwt.getClaims().forEach((k, v) -> result.put(k, v.asString()));
        } catch (Exception e) {
            throw new Exception("token验证失败");
        }
        return result;
    }


}


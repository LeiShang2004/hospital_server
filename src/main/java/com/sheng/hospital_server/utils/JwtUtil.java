package com.sheng.hospital_server.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    // 密钥
    @Value("${jwt.secretKey}")
    private static String SECRET_KEY;
    // 过期时间
    @Value("${jwt.expire}")
    private static Long EXPIRE;


    /**
     * 生成jwt
     * 使用HS256算法，私钥使用固定密钥
     * @param claims 设置的信息
     * @return jwt令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        //指定加密算法
        SecureDigestAlgorithm<SecretKey, SecretKey> algorithm = Jwts.SIG.HS256;
        //生成JWT的时间
        long expMillis = System.currentTimeMillis() + EXPIRE;
        Date exp = new Date(expMillis);
        //密钥实例
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(key, algorithm)
                //如果有私有声明，一点要先设置这个自己创建的私有的声明，这个是给builder的claims赋值，一旦卸载标准的声明赋值之后，就是覆盖了那些标准的声明的
                .expiration(exp)
                //设置自定义负载信息
                .claims(claims)
                .compact();
    }

    /**
     * 解析jwt
     * @param jwt jwt令牌
     * @return jwt的负载
     */
    public static Jws<Claims> parseJWT(String jwt){
        //密钥实例
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt);
    }

}


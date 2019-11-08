package com.member.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.member.model.constant.MemberConstant;
import com.member.model.po.auto.Member;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jwt认证工具类
 *
 * @author f
 * @date 2019-11-07
 */
public class SignUtils {

    /**
     * jwt密钥
     */
    public static final String signingSecret = "SIGNING_SECRET";

    /**
     * 加密算法map缓存
     */
    private static Map<String, Algorithm> algorithmMap = new ConcurrentHashMap<>();

    /**
     * 验证算法map缓存
     */
    private static Map<String, JWTVerifier> verifierMap = new ConcurrentHashMap<>();

    /**
     * 根据密钥获得加密算法
     *
     * @param secret
     * @return
     */
    private static Algorithm getAlgorithm(String secret) {
        Algorithm algorithm = algorithmMap.get(secret);
        if (algorithm == null) {
            algorithm = Algorithm.HMAC512(secret);
            algorithmMap.put(secret, algorithm);
        }
        return algorithm;
    }

    /**
     * 验证token
     *
     * @param token
     * @param secret
     * @return
     */
    public static DecodedJWT verifyToken(String token, String secret) {
        JWTVerifier verifier = verifierMap.get(secret);
        if (verifier == null) {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            verifier = JWT.require(algorithm).build();
            verifierMap.put(secret, verifier);
        }
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }

    /**
     * 生成token
     *
     * @param member   用户
     * @param duration 有效期
     * @return
     */
    public static String generateToken(Member member, long duration) {
        Algorithm algorithm = SignUtils.getAlgorithm(signingSecret);
        String token = JWT.create()
                .withClaim(MemberConstant.MEMBER_ID, member.getId())
                .withClaim(MemberConstant.USER_NAME, member.getUserName())
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .sign(algorithm);
        return token;
    }

}

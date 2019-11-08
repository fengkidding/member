package com.member.model.constant;

/**
 * 鉴权验证常量
 *
 * @author f
 * @date 2019-11-07
 */
public class AuthConstant {

    /**
     * token名称
     */
    public static final String COOKIE_NAME = "token";

    /**
     * 一周
     */
    public static final long LONG_SESSION = 604800000;

    /**
     * 一小时
     */
    public static final long SHORT_SESSION = 3600000;

    private AuthConstant() {
    }
}

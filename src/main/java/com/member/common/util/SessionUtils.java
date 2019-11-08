package com.member.common.util;

import com.alibaba.fastjson.JSON;
import com.member.model.constant.AuthConstant;
import com.member.model.po.auto.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * session工具类
 *
 * @author f
 * @date 2019-11-08
 */
public class SessionUtils {

    /**
     * 用户登陆设置token
     *
     * @param member       用户
     * @param rememberMe   是否记住
     * @param externalApex 域
     * @param response
     */
    public static void loginUser(Member member, boolean rememberMe, String externalApex, HttpServletResponse response) {
        long duration;
        int maxAge;
        if (rememberMe) {
            duration = AuthConstant.LONG_SESSION;
        } else {
            duration = AuthConstant.SHORT_SESSION;
        }
        maxAge = (int) (duration / 1000);
        String token = SignUtils.generateToken(member, duration);
        LogBackUtils.info("SessionUtils.loginUser param=" + JSON.toJSONString(member) + ",token=" + token);

        Cookie cookie = new Cookie(AuthConstant.COOKIE_NAME, token);
        cookie.setPath("/");
        //域
        cookie.setDomain(externalApex);
        //存在时间
        cookie.setMaxAge(maxAge);
        //只读
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        Cookie tokenCookie = Arrays.stream(cookies)
                .filter(cookie -> AuthConstant.COOKIE_NAME.equals(cookie.getName()))
                .findAny().orElse(null);
        if (tokenCookie == null) {
            return null;
        }
        return tokenCookie.getValue();
    }

    /**
     * 用户登出
     *
     * @param externalApex
     * @param response
     */
    public static void logout(String externalApex, HttpServletResponse response) {
        Cookie cookie = new Cookie(AuthConstant.COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setDomain(externalApex);
        response.addCookie(cookie);
    }
}

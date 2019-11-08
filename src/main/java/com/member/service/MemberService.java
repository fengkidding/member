package com.member.service;

import com.member.model.po.auto.Member;

/**
 * 用户信息
 *
 * @author f
 * @date 2018-11-02
 */
public interface MemberService {

    /**
     * 获取用户信息
     *
     * @param userName 用户名称
     * @param password 用户密码
     * @return 用户信息
     */
    Member getUserMsg(String userName, String password);

    /**
     * 更新用户余额
     *
     * @param userName     用户名称
     * @param remainingSum 用户余额
     * @return
     */
    void updateRemainingSum(String userName, Long remainingSum);
}

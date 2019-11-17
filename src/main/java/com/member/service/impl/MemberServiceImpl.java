package com.member.service.impl;

import com.member.dao.db.ext.MemberExtMapper;
import com.member.model.po.auto.Member;
import com.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户信息
 *
 * @author f
 * @date 2018-11-02
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberExtMapper memberExtMapper;

    /**
     * 获取用户信息
     *
     * @param memberName 用户名称
     * @param password 用户密码
     * @return 用户信息
     */
    @Override
    public Member getUserMsg(String memberName, String password) {
        Member member = null;
        List<Member> list = memberExtMapper.getByNameAndPassword(memberName, password);
        if (!CollectionUtils.isEmpty(list)) {
            member = list.get(0);
        }
        return member;
    }

    /**
     * 更新用户余额
     *
     * @param memberId     用户id
     * @param remainingSum 用户余额
     * @return
     */
    @Override
    public void updateRemainingSum(Integer memberId, Long remainingSum) {
        memberExtMapper.updateRemainingSum(memberId, remainingSum);
    }
}

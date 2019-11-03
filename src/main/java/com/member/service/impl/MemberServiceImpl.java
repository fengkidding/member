package com.member.service.impl;

import com.member.model.po.auto.Member;
import com.member.model.vo.view.MemberVO;
import com.member.service.MemberService;
import com.member.common.util.ComputeUtils;
import com.member.dao.db.ext.MemberExtMapper;
import com.member.model.conversion.MemberConversion;
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
     * @param userName 用户名称
     * @param password 用户密码
     * @return 用户信息
     */
    @Override
    public MemberVO getUserMsg(String userName, String password) {
        MemberVO consumerUserVmo = null;
        List<Member> list = memberExtMapper.getByNameAndPassword(userName, password);
        if (!CollectionUtils.isEmpty(list)) {
            Member member = list.get(0);
            consumerUserVmo = MemberConversion.CONSUMER_USER_CONVERSION.dtoToVmo(member);
            consumerUserVmo.setRemainingSum(ComputeUtils.getYuan(member.getRemainingSum()));
        }
        return consumerUserVmo;
    }

    /**
     * 更新用户余额
     *
     * @param userName     用户名称
     * @param remainingSum 用户余额
     * @return
     */
    @Override
    public void updateRemainingSum(String userName, Long remainingSum) {
        memberExtMapper.updateRemainingSum(userName, remainingSum);
    }
}

package com.member.dao.db.ext;

import com.member.dao.db.auto.MemberMapper;
import com.member.model.po.auto.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户mapper
 *
 * @author f
 * @date 2019-03-03
 */
public interface MemberExtMapper extends MemberMapper {

    /**
     * 获取用户信息
     *
     * @param userName 用户名称
     * @param password 用户密码
     * @return
     */
    List<Member> getByNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    /**
     * 更新用户余额
     *
     * @param userName     用户名称
     * @param remainingSum 用户余额
     * @return
     */
    int updateRemainingSum(@Param("userName") String userName, @Param("remainingSum") Long remainingSum);

}

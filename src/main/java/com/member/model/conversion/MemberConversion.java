package com.member.model.conversion;

import com.member.model.po.auto.Member;
import com.member.model.vo.view.MemberVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 用户数据
 *
 * @author f
 * @date 2018-07-11
 */
@Mapper(componentModel = "spring")
public interface MemberConversion {

    /**
     * 常量
     */
    MemberConversion CONSUMER_USER_CONVERSION = Mappers.getMapper(MemberConversion.class);

    /**
     * 用户数据
     *
     * @param member
     * @return
     */
    MemberVO dtoToVmo(Member member);

}

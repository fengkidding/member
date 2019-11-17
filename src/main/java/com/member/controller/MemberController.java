package com.member.controller;

import com.alibaba.fastjson.JSON;
import com.member.common.log.LogBackUtils;
import com.member.common.util.ComputeUtils;
import com.member.common.util.SessionUtils;
import com.member.model.conversion.MemberConversion;
import com.member.model.enums.ResultEnum;
import com.member.model.exception.ServiceException;
import com.member.model.po.auto.Member;
import com.member.model.vo.common.ResultVO;
import com.member.model.vo.param.MemberParamVO;
import com.member.model.vo.param.MemberSumParamVO;
import com.member.model.vo.view.MemberVO;
import com.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 用户
 *
 * @author f
 * @date 2018-04-22
 */
@Api(description = "用户接口")
@RestController
@RequestMapping(value = "/member")
public class MemberController extends BaseController {

    @Autowired
    private MemberService consumerUserService;

    @Value("${EXTERNAL_APEX}")
    private String externalApex;

    /**
     * 用户登陆，获取用户信息
     *
     * @param memberParamVO
     * @return
     */
    @ApiOperation(value = "用户登陆，获取用户信息")
    @GetMapping(value = "/get_user_msg")
    public ResultVO<MemberVO> getUserMsg(@Valid MemberParamVO memberParamVO, HttpServletResponse response) {
        Member member = consumerUserService.getUserMsg(memberParamVO.getMemberName(), memberParamVO.getPassword());
        MemberVO memberVO = null;
        if (null != member) {
            memberVO = MemberConversion.CONSUMER_USER_CONVERSION.dtoToVmo(member);
            memberVO.setRemainingSum(ComputeUtils.getYuan(member.getRemainingSum()));

            SessionUtils.loginUser(member, true, externalApex, response);
        }
        return super.resultSuccess(memberVO);
    }

    /**
     * 更新用户余额
     *
     * @param memberSumParamVO
     * @return
     */
    @ApiOperation(value = "更新用户余额")
    @RequestMapping(value = "/update_remaining_sum", method = RequestMethod.POST)
    public ResultVO updateRemainingSum(@RequestBody @Valid MemberSumParamVO memberSumParamVO) {
        LogBackUtils.info("更新用户余额: consumerUserSumParamVmo=" + JSON.toJSONString(memberSumParamVO));
        consumerUserService.updateRemainingSum(memberSumParamVO.getMemberId(), memberSumParamVO.getRemainingSum());
        return super.resultSuccess();
    }

    /**
     * 用户注销
     *
     * @param memberParamVO
     * @return
     */
    @ApiOperation(value = "用户注销")
    @PostMapping(value = "/login_out")
    public ResultVO<MemberVO> loginOut(@Valid MemberParamVO memberParamVO, HttpServletResponse response) {
        Member member = consumerUserService.getUserMsg(memberParamVO.getMemberName(), memberParamVO.getPassword());
        if (null != member) {
            SessionUtils.logout(externalApex, response);
        } else {
            throw new ServiceException(ResultEnum.MEMBER_NAME_PASSWORD_ERROR);
        }
        return super.resultSuccess();
    }
}

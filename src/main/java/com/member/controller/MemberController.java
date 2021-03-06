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
import com.member.model.vo.common.TokenVO;
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
@Api(tags = {"用户接口"})
@RestController
@RequestMapping(value = "/member")
public class MemberController extends BaseController {

    @Autowired
    private MemberService consumerUserService;

    @Value("${external-apex}")
    private String externalApex;

    @Value("${login-secret}")
    private String loginSecret;

    /**
     * 用户登陆，获取用户信息
     *
     * @param memberParamVO
     * @return
     */
    @ApiOperation(value = "用户登陆，获取用户信息")
    @GetMapping(value = "/get-user-msg")
    public ResultVO<MemberVO> getUserMsg(@Valid MemberParamVO memberParamVO, HttpServletResponse response) {
        Member member = consumerUserService.getUserMsg(memberParamVO.getMemberName(), memberParamVO.getPassword());
        MemberVO memberVO = null;
        if (null != member) {
            memberVO = MemberConversion.CONSUMER_USER_CONVERSION.dtoToVmo(member);
            memberVO.setRemainingSum(ComputeUtils.getYuan(member.getRemainingSum()));

            TokenVO tokenVO = new TokenVO();
            tokenVO.setRememberMe(true);
            tokenVO.setExternalApex(externalApex);
            tokenVO.setMemberId(member.getId());
            tokenVO.setMemberName(member.getMemberName());
            SessionUtils.loginUser(tokenVO, loginSecret, response);
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
    @RequestMapping(value = "/update-remaining-sum", method = RequestMethod.POST)
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
    @PostMapping(value = "/login-out")
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

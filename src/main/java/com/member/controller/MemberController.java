package com.member.controller;

import com.alibaba.fastjson.JSON;
import com.member.common.util.LogUtils;
import com.member.model.vo.common.ResultVO;
import com.member.model.vo.param.MemberParamVO;
import com.member.model.vo.param.MemberSumParamVO;
import com.member.model.vo.view.MemberVO;
import com.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 用户登陆，获取用户信息
     *
     * @param memberParamVO
     * @return
     */
    @ApiOperation(value = "用户登陆，获取用户信息")
    @GetMapping(value = "/get_user_msg")
    public ResultVO<MemberVO> getUserMsg(@Valid MemberParamVO memberParamVO) {
        MemberVO consumerUserVmo = consumerUserService.getUserMsg(memberParamVO.getUserName(), memberParamVO.getPassword());
        return super.resultSuccess(consumerUserVmo);
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
        LogUtils.info("更新用户余额: consumerUserSumParamVmo=" + JSON.toJSONString(memberSumParamVO));
        consumerUserService.updateRemainingSum(memberSumParamVO.getUserName(), memberSumParamVO.getRemainingSum());
        return super.resultSuccess();
    }

}

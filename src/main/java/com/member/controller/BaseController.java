package com.member.controller;


import com.member.factory.ResultFactory;
import com.member.model.enums.ResultEnum;
import com.member.model.vo.common.ResultVO;
import com.member.factory.ResultFactory;
import com.member.model.enums.ResultEnum;
import com.member.model.vo.common.ResultVO;

/**
 * Controller 父类
 *
 * @author f
 * @date 2019-02-22
 */
public class BaseController {

    /**
     * 封装返回成功结果
     *
     * @return
     */
    protected ResultVO resultSuccess() {
        return ResultFactory.getSuccessResult();
    }

    /**
     * 封装返回成功结果
     *
     * @param object
     * @return
     */
    protected ResultVO resultSuccess(Object object) {
        return ResultFactory.getSuccessResult(object);
    }

    /**
     * 封装返回失败结果
     *
     * @return
     */
    protected ResultVO resultError() {
        return ResultFactory.getErrorResult();
    }

    /**
     * 封装返回失败结果
     *
     * @param resultEnum
     * @return
     */
    protected ResultVO result(ResultEnum resultEnum) {
        return ResultFactory.getResult(resultEnum);
    }
}

package com.member.model.vo.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户信息vmo
 *
 * @author f
 * @date 2019-03-03
 */
@ApiModel(value = "用户信息vmo")
public class MemberVO {

    @ApiModelProperty(value = "用户名称")
    private String memberName;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户余额")
    private BigDecimal remainingSum;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public BigDecimal getRemainingSum() {
        return remainingSum;
    }

    public void setRemainingSum(BigDecimal remainingSum) {
        this.remainingSum = remainingSum;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
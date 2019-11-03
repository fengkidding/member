package com.member.model.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户参数vmo
 *
 * @author f
 * @date 2019-03-03
 */
@ApiModel(value = "用户参数vmo")
public class MemberParamVO {

    @ApiModelProperty(value = "用户名称")
    @NotBlank(message = "用户名称不能为空！")
    private String userName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空！")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.member.model.vo.common;

/**
 * token数据vo
 *
 * @author f
 * @date 2019-11-22
 */
public class TokenVO {

    /**
     * 用户id
     */
    private Integer memberId;

    /**
     * 用户名
     */
    private String memberName;

    /**
     * 是否记住登陆
     */
    private Boolean rememberMe;

    /**
     * token域
     */
    private String externalApex;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getExternalApex() {
        return externalApex;
    }

    public void setExternalApex(String externalApex) {
        this.externalApex = externalApex;
    }
}

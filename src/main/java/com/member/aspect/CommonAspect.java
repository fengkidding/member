package com.member.aspect;

import com.member.common.log.LogBackUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * aop
 *
 * @author f
 * @date 2018-04-23
 */
@Aspect
@Component
public class CommonAspect {

    /**
     * AOP处理http请求
     */
    @Pointcut("execution(public * com.member.controller.*.*(..))")
    public void log() {
    }

    /**
     * aop打印异常
     *
     * @param joinPoint
     * @param e
     */
//    @AfterThrowing(throwing = "e", pointcut = "execution(public * com.member.controller.*.*(..))")
//    public void exceptionLog(JoinPoint joinPoint, Throwable e) {
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        StringBuffer sb = new StringBuffer();
//        sb.append("AOP_EXCEPTION: url=");
//        sb.append(request.getRequestURL());
//        sb.append(",参数={");
//        Enumeration<String> enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String str = enu.nextElement();
//            sb.append(str);
//            sb.append("=");
//            sb.append(request.getParameter(str));
//            sb.append(",");
//        }
//        sb.deleteCharAt(sb.length() - 1);
//        sb.append("}");
//        sb.append(",异常=");
//        sb.append(e.getMessage());
//        LogBackUtils.error(sb.toString());
//    }

    /**
     * 请求之前执行
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        LogBackUtils.info("AOP_BEFORE: url=" + request.getRequestURL());
    }

    /**
     * 请求之后执行
     */
    @After("log()")
    public void doAfter(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        LogBackUtils.info("AOP_AFTER: url=" + request.getRequestURL());
    }

}

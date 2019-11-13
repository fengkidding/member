package com.member.common.log.aop;

import com.member.common.log.factory.LogMsgFactory;
import com.member.common.log.model.PerformanceLog;
import com.member.common.log.LogBackUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * Controller Aspect
 *
 * @author f
 * @date 2019-11-13
 */
@Aspect
@Configuration
public class ControllerAccessAspect {

    /**
     * Controller日志
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("com.member.common.log.aop.CommonJoinPoint.ControllerAccessExecution()")
    public Object validate(ProceedingJoinPoint pjp) throws Throwable {
        PerformanceLog performanceLog = LogMsgFactory.getPerformanceLog();
        String packageName = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        String fullName = packageName + "." + methodName;
        performanceLog.setMethodName(fullName);
        performanceLog.start();
        Object result = pjp.proceed();
        performanceLog.stop();
        LogBackUtils.performance(performanceLog);
        return result;
    }

    /**
     * Controller异常日志
     *
     * @param joinPoint
     * @param ex
     */
//    @AfterThrowing(
//            pointcut = "com.member.common.log.aop.CommonJoinPoint.ControllerAccessExecution()",
//            throwing = "ex"
//    )
//    public void doAfterEx(JoinPoint joinPoint, Throwable ex) {
//        LogUtil.logPerformanceAfterEx(joinPoint, ex);
//    }
}
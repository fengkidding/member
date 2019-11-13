package com.member.common.log.aop;

import com.member.common.log.factory.LogMsgFactory;
import com.member.common.log.model.PerformanceLog;
import com.member.common.log.LogBackUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * dao Aspect
 *
 * @author f
 * @date 2019-11-13
 */
@Aspect
@Configuration
public class DaoAccessAspect {

    /**
     * dao日志
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("com.member.common.log.aop.CommonJoinPoint.DaoAccessExecution()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        PerformanceLog performanceLog = LogMsgFactory.getPerformanceLog();
        String packageName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String fullName = packageName + "." + methodName;
        performanceLog.setMethodName(fullName);
        performanceLog.start();
        Object result = joinPoint.proceed();
        performanceLog.stop();
        LogBackUtils.performance(performanceLog);
        return result;
    }

    /**
     * dao异常日志
     *
     * @param joinPoint
     * @param ex
     */
//    @AfterThrowing(
//            pointcut = "com.member.common.log.aop.CommonJoinPoint.DaoAccessExecution()",
//            throwing = "ex"
//    )
//    public void doAfterEx(JoinPoint joinPoint, Throwable ex) {
//        LogUtil.logPerformanceAfterEx(joinPoint, ex);
//    }
}

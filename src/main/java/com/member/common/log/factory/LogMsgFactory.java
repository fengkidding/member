package com.member.common.log.factory;

import com.alibaba.fastjson.JSON;
import com.member.common.log.model.ApplicationLog;
import com.member.common.log.model.LogApplicationContext;
import com.member.common.log.model.LogLevel;
import com.member.common.log.model.PerformanceLog;
import com.member.common.util.RequestUtils;
import com.member.config.FinalEnvConfig;

import java.time.LocalDateTime;

/**
 * log工厂
 *
 * @author f
 * @date 2019-11-13
 */
public class LogMsgFactory {

    /**
     * 获取普通日志对象
     *
     * @param logLevel
     * @param logMessage
     * @return
     */
    public static ApplicationLog getApplicationLog(LogLevel logLevel, String logMessage) {
        ApplicationLog log = new ApplicationLog();
        log.setEnv(FinalEnvConfig.getEnv());
        log.setLogVersion("1.0.0");
        log.setLogTime(LocalDateTime.now().toString());
        log.setTraceId(RequestUtils.getRequetHeader("trace_id"));
        LogApplicationContext context = new LogApplicationContext();
        context.setUrl(RequestUtils.getUrl());
        context.setMethod(RequestUtils.getMethod());
        context.setParams(JSON.toJSONString(RequestUtils.getParams()));
        log.setContext(JSON.toJSONString(context));
        log.setThreadId(Thread.currentThread().getId());
        log.setAppName(FinalEnvConfig.getAppName());
//        log.setFilter(FinalEnvConfig.getFilter());
        log.setServerIp(RequestUtils.getServerIp());
        log.setClientIp(RequestUtils.getClientIp());
        log.setMethodName(getMethodName());
        log.setLevel(logLevel.toString());
        log.setLogMessage(logMessage);
        return log;
    }

    /**
     * 获取性能日志对象
     *
     * @return
     */
    public static PerformanceLog getPerformanceLog() {
        PerformanceLog log = new PerformanceLog();
        log.setEnv(FinalEnvConfig.getEnv());
        log.setLogVersion("1.0.0");
        log.setLogTime(LocalDateTime.now().toString());
        log.setAppName(FinalEnvConfig.getAppName());
        log.setServerIp(RequestUtils.getServerIp());
        log.setClientIp(RequestUtils.getClientIp());
//        log.setFilter(LogHolder.getFilter());
        log.setTraceId(RequestUtils.getRequetHeader("trace_id"));
        return log;
    }

    /**
     * 获取方法名
     *
     * @return
     */
    private static String getMethodName() {
        try {
            StackTraceElement[] stes = Thread.currentThread().getStackTrace();
            for (int i = 1; i < stes.length; ++i) {
                StackTraceElement ste = stes[i];
                if (!ste.getClassName().equals(LogMsgFactory.class.getName()) && !ste.getClassName().contains("common.log")) {
                    return ste.getClassName() + "." + ste.getMethodName();
                }
            }
            return null;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

}
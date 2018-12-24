package com.jdf.mycups.Aspect;

import com.alibaba.fastjson.JSON;
import com.jdf.mycups.annotation.Mylog;
import com.jdf.mycups.dao.po.OperationLog;
import com.jdf.mycups.dao.po.UserInfo;
import com.jdf.mycups.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.security.Principal;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    OperationLogService operationLogService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.jdf.mycups.annotation.Mylog)")
    public void logPoinCut(){

    }

    @AfterReturning("logPoinCut()")
    public void addLog(JoinPoint joinPoint){

        log.info("切面记录日志");

        //开始保存日志
        OperationLog operationLog = new OperationLog();

        //通过反射获取切入点的方法
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();

        Method method = methodSignature.getMethod();

        Mylog mylog = method.getAnnotation(Mylog.class);

        //获取操作
        if (mylog != null) {
            operationLog.setOperation(mylog.value());
        }

        //获取方法
        String className = joinPoint.getTarget().getClass().getName();

        operationLog.setMethod(className+"."+method.getName());
        //获取参数
        Object[] args = joinPoint.getArgs();

        operationLog.setParams(JSON.toJSONString(args));

        //获取用户,根据provider里面设置的,设置userinfo
//        UserInfo principal =(UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取用户,根据provider里面设置的,设置username
        String principals =(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        operationLog.setUsername(principals);


        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        int i = operationLogService.addLog(operationLog);

    }

}

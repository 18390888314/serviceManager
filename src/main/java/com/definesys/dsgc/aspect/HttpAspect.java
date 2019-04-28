package com.definesys.dsgc.aspect;


import com.definesys.dsgc.auth.CustomSSOAuth;
import com.definesys.mpaas.common.adapter.UserProfile;
import com.definesys.mpaas.log.SWordLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class HttpAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    CustomSSOAuth customSSOAuth;

    @Pointcut(value = "(execution(public * com.definesys.dsgc.controller.*.*(..)) )) ")
    public void p(){}


    @Before("p()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map<String, String> header = new HashMap<>();
        Map<String, String> cookies = new HashMap<>();

        header.put("token", request.getHeader("token"));
        header.put("uid", request.getHeader("uid"));
        header.put("userName", request.getHeader("userName"));
        header.put("isAdmin", request.getHeader("isAdmin"));
        LOGGER.debug("                                 ");
        LOGGER.debug("------------------------请求信息------------------------");
        //URL
        LOGGER.debug("URL = {}",request.getRequestURI());
        //Method
        LOGGER.debug("Method = {}",request.getMethod());
        //IP
        LOGGER.debug("IP = {}",request.getRemoteAddr());
        //Class.Method
        LOGGER.debug("CLass.Method = {}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+"()");
        //Args
        LOGGER.debug("Args = {}",joinPoint.getArgs());
        // ParameterMap
        LOGGER.debug("ParameterMap = {}", request.getParameterMap().toString());
        // ContentType
        LOGGER.debug("ContentType = {}", request.getContentType());
        // uid
//        LOGGER.warn("uid = {}", userProfile.getUid());
        LOGGER.debug("------------------------请求信息------------------------");
        LOGGER.debug("                                 ");
        if("/dsgc/user/login".equals(request.getRequestURI())){
            return;
        }
//        CustomSSOAuth customSSOAuth = new CustomSSOAuth();
        UserProfile userProfile = customSSOAuth.ssoAuth(header, cookies);
    }

    @After("p()")
    public void doAfterCustom(){
        LOGGER.debug("HttpAspect DoAfter Running" );
    }



}
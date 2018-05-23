package com.weimi.formx.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.weimi.formx.common.request.BaseRequest;
import com.weimi.formx.common.response.ResponseCode;
import com.weimi.formx.common.response.ResponseData;
import com.weimi.formx.exception.FormxException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Method;

/**
 * Created by yangsh on 2018-05-16
 */
@Slf4j
@Aspect
@Component
public class ControllerInterceptor {

    /**
     * 定义拦截规则: 拦截 com.weimi.mcl..*.controller 包下面的所有类中, 有 @RequestMapping 注解的方法
     */
    @Pointcut("execution(* com.weimi.formx..*Controller..*(com.weimi.formx.common.request.BaseRequest+,org.springframework.validation.BindingResult)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut() {
    }

    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) {
        long beginTime = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); // 获取被拦截的方法
        Object[] args = pjp.getArgs();
        BaseRequest req = (BaseRequest) args[0];
        BindingResult result = (BindingResult) args[1];
        if (result.hasErrors()) {
            FieldError fe = result.getFieldErrors().get(0);

            String defaultMessage = fe.getDefaultMessage();

            if (defaultMessage != null && defaultMessage.contains("Failed to convert property value")) {
                log.info("{} [{}={}]", "类型转换错误", fe.getField(), fe.getRejectedValue());
                return ResponseData.fail(ResponseCode.PARAM_TYPE_ERROR);
            } else {
                log.info("{} [{}={}]", defaultMessage, fe.getField(), fe.getRejectedValue());
                return ResponseData.fail(ResponseCode.PARAM_INCOMPLETE, fe.getDefaultMessage());
            }
        }



        Object res = null;
        try {
            ObjectMapper om = new ObjectMapper();
            om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            log.info("{} started", om.writeValueAsString(req));
            res = pjp.proceed();
            log.info("{} completed in {} ms", om.writeValueAsString(res), System.currentTimeMillis() - beginTime);
        } catch (FormxException e) {
            e.printStackTrace();
            return ResponseData.fail(e.getResponseCode());
        } catch (Throwable e) {
            log.error("exception: {}", e.getMessage());
            return ResponseData.fail(ResponseCode.EXCEPTION);
        }

        return res;
    }

}

package com.dyx.test.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@ControllerAdvice

//@ExceptionHandler  统一处理莫一类异常，从而能够减少代码重复率和复杂度
//@ControllerAdvice  异常几种处理，更好的使业务逻辑与异常处理剥离开

public class GloabalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req,Exception e) throws  Exception{
        e.printStackTrace();
        Class constrainViolationException = Class.forName("org.hibernate.exception.ConstraintViolationException");
        if(null!=e.getCause() && constrainViolationException==e.getCause().getClass()){
            return "违反了约束，多半是外键约束";
        }
        return e.getMessage();
    }
}
























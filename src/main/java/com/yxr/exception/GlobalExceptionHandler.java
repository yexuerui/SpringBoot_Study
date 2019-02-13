package com.yxr.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * ExceptionHandler
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> errorResult(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("error","123error");
        return map;
    }
}

package com.example.demo.exception;

import com.example.demo.enums.DemoEnum;
import com.example.demo.util.DemoResponseUtils;
import com.example.demo.vo.DemoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class WebExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    public WebExceptionHandler() {
    }

    @ExceptionHandler({DemoException.class})
    @ResponseBody
    public DemoResponse<?> handleDemoException(DemoException e) {
        log.debug(e.toString(), e);
        return DemoResponseUtils.create(e.getCode(), e.getMessage(), e.getSubMessage());
    }


    @ExceptionHandler({Exception.class})
    @ResponseBody
    public DemoResponse<Void> handleException(Exception e) {
        log.error(DemoEnum.EXCEPTION.getMessage(), e);
        return DemoResponseUtils.create(DemoEnum.EXCEPTION, e.getMessage());
    }
}

package com.example.demo.exception;

import com.alibaba.fastjson.JSON;
import com.example.demo.enums.DemoEnum;
import com.example.demo.vo.DemoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常捕获
     * @param e 捕获的异常
     * @return 封装的返回对象
     **/
    @ExceptionHandler(Exception.class)
    public DemoResult handlerException(Exception e) {
        DemoEnum demoEnum;
            // 其他异常，当我们定义了多个异常时，这里可以增加判断和记录
            demoEnum = DemoEnum.SERVER_ERROR;
            demoEnum.setMessage(e.getMessage());
            log.error("common exception:{}", JSON.toJSONString(e));
        return DemoResult.failure(demoEnum);
    }

    /**
     * 获取错误信息
     * @param ex
     * @return
     */
    private String getConstraintViolationErrMsg(Exception ex) {
        // validTest1.id: id必须为正数
        // validTest1.id: id必须为正数, validTest1.name: 长度必须在有效范围内
        String message = ex.getMessage();
        try {
            int startIdx = message.indexOf(": ");
            if (startIdx < 0) {
                startIdx = 0;
            }
            int endIdx = message.indexOf(", ");
            if (endIdx < 0) {
                endIdx = message.length();
            }
            message = message.substring(startIdx, endIdx);
            return message;
        } catch (Throwable throwable) {
            log.info("ex caught", throwable);
            return message;
        }
    }
}

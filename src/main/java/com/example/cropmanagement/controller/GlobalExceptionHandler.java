package com.example.cropmanagement.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // 声明这是一个全局增强器
public class GlobalExceptionHandler {

    /**
     * 专门捕获【参数校验失败】引发的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, Object> response = new HashMap<>();
        // 拿到我们在实体类里写的那个 message
        // 这种写法更安全：如果拿不到错误消息，就返回默认提示
        String message = "参数输入有误";
        if (e.getBindingResult().getFieldError() != null) {
            message = e.getBindingResult().getFieldError().getDefaultMessage();
        }

        response.put("code", 400);
        response.put("msg", message);
        response.put("data", null);
        return response;
    }

    /**
     * 捕获【其他所有】未知的运行报错
     */

    // 在 GlobalExceptionHandler 中添加这个方法，专门处理 404
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public Map<String, Object> handle404(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 404);
        response.put("msg", "你要找的地方不存在，请检查 URL 是否正确。");
        return response;
    }
}
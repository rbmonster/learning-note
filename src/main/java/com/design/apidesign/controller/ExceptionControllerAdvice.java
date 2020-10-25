package com.design.apidesign.controller;

import com.design.apidesign.controller.other.ApiException;
import com.design.apidesign.controller.other.JsonResponse;
import com.design.apidesign.controller.other.SystemCode;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * <pre>
 * @Description:
 * RestControllerAdvice 负责处理全局的Controller 异常
 * 根据ExceptionHandler的类型调用对应的处理方法
 * </pre>
 *
 * @version v1.0
 * @ClassName: ExceptionControllerAdvice
 * @Author: sanwu
 * @Date: 2020/10/24 13:15
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 全局MethodArgumentNotValidException 处理
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        List<ObjectError> objectErrorList = e.getBindingResult().getAllErrors();
        StringBuilder sb = new StringBuilder();
        objectErrorList.stream().map(ObjectError::getDefaultMessage).forEach(str -> sb.append(str+"\n"));
        // 然后提取错误提示信息进行返回
        return sb.toString();
    }

    /**
     * 全局自定义异常处理 并返回标准的包装类型
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public JsonResponse<String> APIExceptionHandler(ApiException e) {
        return new JsonResponse<>(SystemCode.FAILURE,e.getMsg());
    }
}

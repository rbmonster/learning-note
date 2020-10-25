package com.design.apidesign.controller;

import com.design.apidesign.controller.other.ApiException;
import com.design.apidesign.controller.other.JsonResponse;
import com.design.apidesign.controller.other.SystemCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <pre>
 * @Description:
 * 返回参数统一处理
 * </pre>
 *
 * @version v1.0
 * @ClassName: ResponseControllerAdvice
 * @Author: sanwu
 * @Date: 2020/10/24 13:37
 */
@RestControllerAdvice(basePackages = {"com.design.apidesign.controller"}) // 注意哦，这里要加上需要扫描的包
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是JsonResponse那就没有必要进行额外的操作，返回false
        return !methodParameter.getGenericParameterType().equals(JsonResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (methodParameter.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在JsonResponse里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new JsonResponse<>(data));
            } catch (JsonProcessingException e) {
                throw new ApiException("返回String类型错误");
            }
        } else if(methodParameter.getGenericParameterType().equals(JsonResponse.class)){
            int code = ((JsonResponse)data).getCode();
            HttpMethod method = serverHttpRequest.getMethod();
            serverHttpResponse.setStatusCode(SystemCode.parseHttpMethod(method, code));
            return data;
        } else {
            // 将原本的数据包装在JsonResponse里
            return new JsonResponse<>(data);
        }
    }
}

package com.design.apidesign.controller.other;

import lombok.Getter;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: ApiException
 * @Author: sanwu
 * @Date: 2020/10/24 13:26
 */
@Getter
public class ApiException extends RuntimeException {
    private int code;
    private String msg;

    public ApiException() {
        this(SystemCode.FAILURE, "接口错误");
    }

    public ApiException(String msg) {
        this(SystemCode.FAILURE, msg);
    }

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}

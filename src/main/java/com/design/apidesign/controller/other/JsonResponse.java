package com.design.apidesign.controller.other;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @param <T>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class JsonResponse<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    private String error;

    public JsonResponse() {
        this.code = SystemCode.SUCCESS;
    }
    public JsonResponse(T data) {
        this.code = SystemCode.SUCCESS;
        this.data = data;
    }

    public JsonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public JsonResponse setCode(int status) {
        this.code = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JsonResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public JsonResponse setData(T data) {
        this.data = data;
        return this;
    }

    public String getError() {
        return error;
    }

    public JsonResponse setError(String error) {
        this.error = error;
        return this;
    }

    public JsonResponse setSuccess(String msg) {
        this.code = SystemCode.SUCCESS;
        this.message = msg;
        return this;
    }

    public JsonResponse setFail(String error) {
        this.code = SystemCode.FAILURE;
        this.error = error;
        return this;
    }
}

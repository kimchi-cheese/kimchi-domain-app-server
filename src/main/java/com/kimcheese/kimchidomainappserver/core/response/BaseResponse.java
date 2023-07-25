package com.kimcheese.kimchidomainappserver.core.response;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponse<T> {
    private final boolean isSuccess;
    private final String message;
    private final int code;
    private final T result;


    public BaseResponse(HttpStatus status, T result) {
        this.isSuccess = true;
        this.message = "요청에 성공하였습니다.";
        this.code = status.value();
        this.result = result;
    }
}

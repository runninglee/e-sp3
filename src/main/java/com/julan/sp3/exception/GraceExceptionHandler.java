package com.julan.sp3.exception;

import com.julan.sp3.util.api.ResultJson;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GraceExceptionHandler {

    @ExceptionHandler(GraceException.class)
    public ResultJson<Object> handler(GraceException e) {
        return ResultJson.failed(e.getMessage(),e.code);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResultJson<Object> handler(EmptyResultDataAccessException e) {
        return ResultJson.failed("数据不存在");
    }

    @ExceptionHandler(Exception.class)
    public ResultJson<Object> handler(Exception e) {
        return ResultJson.failed(e.getMessage());
    }
}

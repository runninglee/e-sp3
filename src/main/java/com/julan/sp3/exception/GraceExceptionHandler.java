package com.julan.sp3.exception;

import com.julan.sp3.util.api.ResultJson;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@RestControllerAdvice
public class GraceExceptionHandler {

    @ExceptionHandler(GraceException.class)

    public ResultJson<Object> handler(GraceException e) {
        return ResultJson.failed(e.getMessage(), e.code);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResultJson<Object> handler(EmptyResultDataAccessException e) {
        return ResultJson.failed("数据不存在");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultJson<Object> handler(MethodArgumentNotValidException e) {
        //全量显示，显示不优雅
        //        HashMap<String, String> map = new HashMap<>();
        //        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        //        for (FieldError error : errors) {
        //            String field = error.getField();
        //            String message = error.getDefaultMessage();
        //            map.put(field, message);
        //        }
        //        return ResultJson.validateFailed(map);
        //单条显示，优雅
        List<FieldError> errors = e.getFieldErrors();
        return ResultJson.validateFailed(errors.get(0).getDefaultMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultJson<Object> handler(NoHandlerFoundException e) {
        return ResultJson.failed(e.getMessage(), 404);
    }

    @ExceptionHandler(Exception.class)
    public ResultJson<Object> handler(Exception e) {
        return ResultJson.failed(e.getMessage());
    }
}

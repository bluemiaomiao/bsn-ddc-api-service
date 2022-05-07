package io.github.bluemiaomiao.bsnddcapiservice.handlers.exception;

import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@ResponseBody
@RestControllerAdvice(value = {"io.github.bluemiaomiao.bsnddcapiservice", "org.spring"})
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleException(Exception e) {
        String message = String.format("未定义的错误类型: %s", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(null).withCode("1110").withMessage(message),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

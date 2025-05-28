package com.apple.shop.core.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    // 예외 처리 메서드를 여기에 추가할 수 있습니다.
    // 예를 들어, 특정 예외를 처리하는 메서드를 작성할 수 있습니다.
    // @ExceptionHandler(예외.class)
    // public ResponseEntity<ErrorResponse> handleException(예외 e) {
    //     // 예외 처리 로직
    // }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(){
        return ResponseEntity.status(400).body("에러나냐?");
    }
}

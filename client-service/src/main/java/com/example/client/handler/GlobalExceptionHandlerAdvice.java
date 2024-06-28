package com.example.client.handler;


import com.example.client.vo.ErrorMessageVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageVo> handleGlobalException(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String campo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(campo, mensaje);
        });

        ErrorMessageVo error = ErrorMessageVo.builder()
                .status(Integer.toString(HttpStatus.BAD_REQUEST.value()))
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errors(errores)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageVo> handleGlobalException(Exception ex) {
        ErrorMessageVo error = ErrorMessageVo.builder()
                .status(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

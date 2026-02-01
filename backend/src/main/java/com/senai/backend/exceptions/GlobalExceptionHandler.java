package com.senai.backend.exceptions;

import com.senai.backend.exceptions.dtos.ParametroInvalidoDto;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ProblemDetail handleApplictionException(ApplicationException e){
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var parametrosInvalidos = e.getFieldErrors()
                .stream()
                .map(fe -> new ParametroInvalidoDto(fe.getField(), fe.getDefaultMessage()))
                .toList();

        var pd = ProblemDetail.forStatus(400);

        pd.setTitle("Parâmetros Inconsistestes");
        pd.setDetail("Existem campos inválidos na solicitação.");
        pd.setProperty("Parâmetros inválidos", parametrosInvalidos);

        return pd;
    }
}

package com.senai.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EntityNotFoundException extends ApplicationException{

    private final String detail;

    public EntityNotFoundException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pd.setTitle("Informação solicitada não encontrato");
        pd.setDetail(detail);

        return pd;
    }
}

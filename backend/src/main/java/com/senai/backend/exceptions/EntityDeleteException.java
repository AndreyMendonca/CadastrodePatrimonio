package com.senai.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EntityDeleteException extends ApplicationException{

    private final String detail;

    public EntityDeleteException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        pd.setTitle("Violação de dados no processo de deleção");
        pd.setDetail(detail);

        return pd;
    }
}

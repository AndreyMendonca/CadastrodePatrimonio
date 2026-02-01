package com.senai.backend.exceptions;

import org.springframework.http.ProblemDetail;

public abstract class ApplicationException extends RuntimeException{
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(500);

        pd.setTitle("Error");
        pd.setDetail("Conecte com o suporte!");

        return pd;
    }
}

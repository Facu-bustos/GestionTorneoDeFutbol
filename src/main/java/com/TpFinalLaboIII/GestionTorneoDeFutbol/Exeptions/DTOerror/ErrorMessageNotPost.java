package com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.DTOerror;

import org.springframework.http.HttpStatus;

public class ErrorMessageNotPost {
    private HttpStatus httpStatus;
    private String message;

    public ErrorMessageNotPost(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public ErrorMessageNotPost(){}

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.DTOerror;


import org.springframework.http.HttpStatus;


public class ErrorMessageNotFound {
     private HttpStatus status;
     private String message;

    public ErrorMessageNotFound(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    public ErrorMessageNotFound() {

    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

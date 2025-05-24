package com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.DTOerror.ErrorMessageNotPost;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiRestResponseEntityExceptionNotPost extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotPostException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageNotPost>notPostException(NotPostException notPostException)
    {
        ErrorMessageNotPost errorMessageNotPost = new ErrorMessageNotPost(HttpStatus.BAD_REQUEST, notPostException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageNotPost);
    }

}

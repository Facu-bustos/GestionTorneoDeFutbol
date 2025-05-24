package com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.EntityErrors;

import com.TpFinalLaboIII.GestionTorneoDeFutbol.Exeptions.DTOerror.ErrorMessageNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiRestResponseEntityExeptionHandlerNotFound extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessageNotFound>notFoundException(NotFoundException notFoundexception)
    {
        ErrorMessageNotFound errorMessageNotFound = new ErrorMessageNotFound(HttpStatus.NOT_FOUND,notFoundexception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageNotFound);
    }

}

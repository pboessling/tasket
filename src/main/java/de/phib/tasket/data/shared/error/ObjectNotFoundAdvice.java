package de.phib.tasket.data.shared.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ControllerAdvice to intercept ObjectNotFoundExceptions.
 */
@ControllerAdvice
class ObjectNotFoundAdvice {

    /**
     * Intercepts an ObjectNotFoundException and returns status code 404 with the exception message as response body.
     *
     * @param e the exception
     * @return the exception message as response body
     */
    @ResponseBody
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String objectNotFoundHandler(ObjectNotFoundException e) {
        return e.getMessage();
    }
}

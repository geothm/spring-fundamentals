package ro.wantsome.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NoBookFoundException.class)
    public ResponseEntity handleNoBookException() {

        ApiError apiError = new ApiError(404, "not found", "Book not found");

        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGlobalException(Exception ex) {
        return new ResponseEntity(new ApiError(500, ex.getMessage(), "Global exception") ,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package br.com.asmaproject.config;

import br.com.asmaproject.exception.ApiResponse;
import br.com.asmaproject.exception.EmailIndisponivelException;
import br.com.asmaproject.exception.InvalidParameterException;
import br.com.asmaproject.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = EmailIndisponivelException.class)
    public ResponseEntity<Object> handleValidationException(EmailIndisponivelException ex) {
        ApiResponse<?> errorResponse = new ApiResponse<>(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleValidationException(NotFoundException ex) {
        ApiResponse<?> errorResponse = new ApiResponse<>(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<Object> handleValidationException(InvalidParameterException ex) {
        ApiResponse<?> errorResponse = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
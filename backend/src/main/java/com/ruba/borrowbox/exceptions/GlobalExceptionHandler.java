package com.ruba.borrowbox.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import com.ruba.borrowbox.exceptions.BorrowRequestException;
import org.springframework.http.ResponseEntity;

// to return the error message in the form of json!

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public ErrorResponse handleItemNotFound (ItemNotFoundException exception){
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                LocalDateTime.now()
        );
    }
    @ExceptionHandler(BorrowRequestException.class)
    public ResponseEntity<ErrorResponse> handleBorrowRequestException(
            BorrowRequestException ex) {
        ErrorResponse error = new ErrorResponse(
                400,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

}
package com.ruba.borrowbox.exceptions;

public class BorrowRequestException extends RuntimeException {
    public BorrowRequestException(String message) {
        super(message);
    }
}

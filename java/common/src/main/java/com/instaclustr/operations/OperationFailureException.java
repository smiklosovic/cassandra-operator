package com.instaclustr.operations;

public class OperationFailureException extends RuntimeException {

    public OperationFailureException(String message) {
        super(message);
    }

    public OperationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}

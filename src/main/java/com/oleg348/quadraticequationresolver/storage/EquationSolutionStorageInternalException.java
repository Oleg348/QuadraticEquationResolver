package com.oleg348.quadraticequationresolver.storage;

/** Represents internal error happened in storage. */
public class EquationSolutionStorageInternalException extends RuntimeException {

    public EquationSolutionStorageInternalException(String message) {
        super(message);
    }

    public EquationSolutionStorageInternalException(Throwable cause) {
        super(cause);
    }

    public EquationSolutionStorageInternalException(String message, Throwable cause) {
        super(message, cause);
    }
}

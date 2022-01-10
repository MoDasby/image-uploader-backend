package com.modasby.imageUploader.exception;

public class CannotSaveFileException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CannotSaveFileException(String message) {
        super(message);
    }

    public CannotSaveFileException(String message, Throwable cause) {
        super(message, cause);
    }
}

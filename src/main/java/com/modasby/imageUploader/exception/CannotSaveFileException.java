package com.modasby.imageUploader.exception;

import java.io.Serial;

public class CannotSaveFileException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CannotSaveFileException(String message) {
        super(message);
    }
}

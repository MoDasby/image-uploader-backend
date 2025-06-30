package com.modasby.imageUploader.exception;

import java.time.Instant;

public record ExceptionResponse(
        Instant timestamp,
        String message
) {
}

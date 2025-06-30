package com.modasby.imageUploader.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.Instant;

@ControllerAdvice
public class ImageUploaderExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ImageUploaderExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleExceptions(Exception e) {
        var response = new ExceptionResponse(
                Instant.now(),
                "Internal Server Error"
        );

        logger.error("{}: {}", e.getClass().getSimpleName(), response.message());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotFound(ResourceNotFoundException e) {
        var response = new ExceptionResponse(
                Instant.now(),
                e.getMessage()
        );

        logger.error("{}: {}", e.getClass().getSimpleName(), response.message());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public final ResponseEntity<ExceptionResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        var response = new ExceptionResponse(
                Instant.now(),
                e.getMessage()
        );

        logger.error("{}: {}", e.getClass().getSimpleName(), response.message());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotSaveFileException.class)
    public final ResponseEntity<ExceptionResponse> handleCannotSaveFileException(CannotSaveFileException e) {
        var response = new ExceptionResponse(
                Instant.now(),
                e.getMessage()
        );

        logger.error("{}: {}", e.getClass().getSimpleName(), response.message());

        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(UnsupportedFileTypeException.class)
    public final ResponseEntity<ExceptionResponse> handleUnsupportedFileType(UnsupportedFileTypeException e) {
        var response = new ExceptionResponse(
                Instant.now(),
                e.getMessage()
        );

        logger.error("{}: {}", e.getClass().getSimpleName(), response.message());

        return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}

package com.springproject.rest.webservices.exceptionHandler;

import java.time.LocalDateTime;

public class ErrorDetails {
    static LocalDateTime timestamp;
    static String message;
    static String statusCode;

    public ErrorDetails(LocalDateTime timestamp, String message, String statusCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }
}

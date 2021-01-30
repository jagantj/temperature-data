package com.gerimedica.temperature.data.exception;

public class FileTypeNotSupportedException extends RuntimeException {

    private final String errorMessage;

    public FileTypeNotSupportedException(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public FileTypeNotSupportedException(final Throwable ex) {
        super(ex);
        this.errorMessage = null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

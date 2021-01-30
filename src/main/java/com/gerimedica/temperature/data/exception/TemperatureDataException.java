package com.gerimedica.temperature.data.exception;

public class TemperatureDataException extends RuntimeException {

    private final String errorMessage;

    public TemperatureDataException(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public TemperatureDataException(final Throwable ex, final String errorMessage) {
        super(ex);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

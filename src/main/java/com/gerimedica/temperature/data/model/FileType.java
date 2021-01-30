package com.gerimedica.temperature.data.model;

import java.util.Arrays;

import com.gerimedica.temperature.data.exception.FileTypeNotSupportedException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FileType {

                      CSV("text/csv");

    private final String contentType;

    public static FileType from(final String contentType) {
        return Arrays.stream(FileType.values())
            .filter(e -> contentType != null && e.getContentType() != null && contentType.equals(e.getContentType()))
            .findAny().orElseThrow(() -> new FileTypeNotSupportedException("File type not supported"));
    }
}

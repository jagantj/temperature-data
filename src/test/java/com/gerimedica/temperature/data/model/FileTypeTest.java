package com.gerimedica.temperature.data.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gerimedica.temperature.data.exception.FileTypeNotSupportedException;

class FileTypeTest {

    @Test
    void testFileType() {
        assertEquals(FileType.CSV, FileType.from("text/csv"));
    }

    @Test
    void testUnknownFileType() {
        assertThrows(FileTypeNotSupportedException.class, () -> FileType.from("text/plain"));
    }

}

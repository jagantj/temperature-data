package com.gerimedica.temperature.data.Parser;

import static com.gerimedica.temperature.data.utils.MockUtils.mockCSVFile;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.gerimedica.temperature.data.parser.CSVParser;

class CSVParserTest {

    @Test
    @DisplayName("Test csv file is parsed successfully with one record")
    void test() {
        final var file = mockCSVFile("exercise-with-1-records.csv");
        final var result = new CSVParser().parse(file);
        final var firstRecord = result.get(0);

        assertAll("assert first data", () -> assertEquals("ZIB", firstRecord.getSource()), //
            () -> assertEquals("ZIB001", firstRecord.getCodeListCode()), //
            () -> assertEquals("271636001", firstRecord.getCode()), //
            () -> assertEquals("Polsslag regelmatig", firstRecord.getDisplayValue()), //
            () -> assertEquals("The long description is necessary", firstRecord.getLongDescription()),
            () -> assertEquals("1", firstRecord.getSortingPriority()),
            () -> assertEquals(LocalDate.of(2019, 1, 1), firstRecord.getFromDate()),
            () -> assertEquals(StringUtils.EMPTY, firstRecord.getToDate()));
    }

    @Test
    @DisplayName("Test csv file with multiple file parsed successfully")
    void test1() {
        final var file = mockCSVFile("exercise.csv");
        final var result = new CSVParser().parse(file);
        assertEquals(18, result.size());
    }

}

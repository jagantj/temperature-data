package com.gerimedica.temperature.data.parser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.temperature.data.exception.TemperatureDataException;
import com.gerimedica.temperature.data.model.TemperatureData;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVParser implements Parser {

    @Override
    public List<TemperatureData> parse(final MultipartFile file) {
        final InputStreamReader reader;
        try {
            reader = new InputStreamReader(file.getInputStream());
            return new CsvToBeanBuilder<TemperatureData>(reader) //
                .withType(TemperatureData.class) //
                .build() //
                .parse();
        } catch (final IOException ex) {
            throw new TemperatureDataException(ex, "Exception while parsing csv");
        }
    }
}

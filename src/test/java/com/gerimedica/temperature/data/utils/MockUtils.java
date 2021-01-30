package com.gerimedica.temperature.data.utils;

import java.time.LocalDate;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import com.gerimedica.temperature.data.model.TemperatureData;

import lombok.SneakyThrows;

public class MockUtils {

    @SneakyThrows
    public static MockMultipartFile mockCSVFile(final String fileName) {
        return new MockMultipartFile("file", fileName, "text/csv", new ClassPathResource(fileName).getInputStream());
    }

    public static TemperatureData mockTemperatureData(final String code) {
        final TemperatureData temperatureData = new TemperatureData();
        temperatureData.setCode(code);
        temperatureData.setCodeListCode("ZIB001");
        temperatureData.setDisplayValue("Polsslag regelmatig");
        temperatureData.setFromDate(LocalDate.now());
        return temperatureData;
    }
}

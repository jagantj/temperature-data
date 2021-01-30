package com.gerimedica.temperature.data.service;

import static com.gerimedica.temperature.data.utils.MockUtils.mockTemperatureData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gerimedica.temperature.data.dao.TemperatureDataRepository;
import com.gerimedica.temperature.data.utils.MockUtils;

@ExtendWith(MockitoExtension.class)
public class TemperatureDataServiceTest {

    private TemperatureDataService temperatureDataService;

    @Mock
    private TemperatureDataRepository temperatureDataRepository;

    @BeforeEach
    void setUp() {
        temperatureDataService = new TemperatureDataService(temperatureDataRepository);
    }

    @Test
    @DisplayName("save all data")
    void test() {
        final var temperatureDataList = List.of(mockTemperatureData("271636001"), mockTemperatureData("Type 1"));
        when(temperatureDataRepository.saveAll(anyIterable())).thenReturn(temperatureDataList);
        final var file = MockUtils.mockCSVFile("exercise-with-1-records.csv");
        final var result = temperatureDataService.save(file);
        assertEquals(2, result.size());
        assertEquals("271636001", result.get(0).getCode());
        assertEquals("Type 1", result.get(1).getCode());
    }

    @Test
    @DisplayName("fetch all data")
    void test1() {
        final var temperatureDataList = List.of(mockTemperatureData("271636001"), mockTemperatureData("Type 1"));
        when(temperatureDataRepository.findAll()).thenReturn(temperatureDataList);

        final var result = temperatureDataService.fetchAll();
        assertEquals(2, result.size());
        assertEquals("271636001", result.get(0).getCode());
        assertEquals("Type 1", result.get(1).getCode());
    }

    @Test
    @DisplayName("fetch by code")
    void test2() {
        when(temperatureDataRepository.findById("271636001")).thenReturn(Optional.of(mockTemperatureData("271636001")));

        final var result = temperatureDataService.fetchByCode("271636001");
        assertEquals("271636001", result.getCode());
    }
}

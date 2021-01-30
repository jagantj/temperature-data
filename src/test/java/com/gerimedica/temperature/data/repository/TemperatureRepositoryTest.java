package com.gerimedica.temperature.data.repository;

import static com.gerimedica.temperature.data.utils.MockUtils.mockTemperatureData;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gerimedica.temperature.data.dao.TemperatureDataRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class TemperatureRepositoryTest {

    @Autowired
    private TemperatureDataRepository temperatureDataRepository;

    @Test
    @DisplayName("Ensure two records inserted in Employee table")
    void test() {
        final var temperatureDataList = List.of(mockTemperatureData("271636001"), mockTemperatureData("Type 1"));
        final var result = temperatureDataRepository.saveAll(temperatureDataList);
        final var resultList = StreamSupport.stream(result.spliterator(), false).collect(Collectors.toList());
        assertEquals(2, resultList.size());
    }

}

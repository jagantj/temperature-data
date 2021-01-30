package com.gerimedica.temperature.data.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.temperature.data.dao.TemperatureDataRepository;
import com.gerimedica.temperature.data.model.TemperatureData;
import com.gerimedica.temperature.data.parser.Parser;
import com.gerimedica.temperature.data.parser.ParserFactory;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TemperatureDataService {

    private final TemperatureDataRepository temperatureDataRepository;

    public List<TemperatureData> save(final MultipartFile file) {
        final Parser parser = ParserFactory.getParser(file);
        final List<TemperatureData> temperatureDataList = parser.parse(file);
        final Iterable<TemperatureData> temperatureData = temperatureDataRepository.saveAll(temperatureDataList);
        return StreamSupport.stream(temperatureData.spliterator(), false).collect(Collectors.toList());
    }

    public List<TemperatureData> fetchAll() {
        final Iterable<TemperatureData> temperatureData = temperatureDataRepository.findAll();
        return StreamSupport.stream(temperatureData.spliterator(), false).collect(Collectors.toList());
    }

    public TemperatureData fetchByCode(final String code) {
        final Optional<TemperatureData> temperatureData = temperatureDataRepository.findById(code);
        return temperatureData.orElse(new TemperatureData());
    }

    public void deleteAll() {
        temperatureDataRepository.deleteAll();
    }

}

package com.gerimedica.temperature.data.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.temperature.data.model.TemperatureData;
import com.gerimedica.temperature.data.service.TemperatureDataService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TemperatureDataController {

    private final TemperatureDataService temperatureDataService;

    @PostMapping(value = "/api/upload-temperature-data", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<TemperatureData>> uploadHealthData(@RequestParam("file") final MultipartFile file) {
        temperatureDataService.save(file);
        return ResponseEntity.ok(temperatureDataService.save(file));
    }

    @GetMapping(value = "/api/temperature-data", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<TemperatureData>> fetchTemperatureData() {
        return ResponseEntity.ok(temperatureDataService.fetchAll());
    }

    @GetMapping(value = "/api/temperature-data/{code}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<TemperatureData> fetchByCode(@PathVariable final String code) {

        return ResponseEntity.ok(temperatureDataService.fetchByCode(code));
    }

    @DeleteMapping(value = "/api/temperature-data/", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> deleteAll() {
        temperatureDataService.deleteAll();
        return ResponseEntity.ok("Deleted Successfully");
    }

}

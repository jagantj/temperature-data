package com.gerimedica.temperature.data.parser;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.temperature.data.model.TemperatureData;

public interface Parser {

    List<TemperatureData> parse(MultipartFile file);
}

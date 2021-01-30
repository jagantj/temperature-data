package com.gerimedica.temperature.data.dao;

import org.springframework.data.repository.CrudRepository;

import com.gerimedica.temperature.data.model.TemperatureData;

public interface TemperatureDataRepository extends CrudRepository<TemperatureData, String> {
}

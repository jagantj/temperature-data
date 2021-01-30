package com.gerimedica.temperature.data.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opencsv.bean.CsvDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Table
@Entity
public class TemperatureData {

    private String source;
    private String codeListCode;
    @Id
    private String code;
    private String displayValue;
    private String longDescription;

    @CsvDate(value = "dd-MM-yyyy")
    private LocalDate fromDate;
    private String toDate;
    private String sortingPriority;
}

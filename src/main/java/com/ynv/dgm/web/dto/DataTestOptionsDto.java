package com.ynv.dgm.web.dto;


import com.ynv.dgm.model.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DataTestOptionsDto {

    private int delayInSeconds;
    private Data.MeasurementType[] measurementTypes;
}

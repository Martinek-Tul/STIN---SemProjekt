package org.example.stinsem.model;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateResponse {
    private Map<String, Double> rates;
}

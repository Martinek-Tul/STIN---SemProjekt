package model;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateResponseDate {
    private Map<String, Map<String, Double>> rates;

}

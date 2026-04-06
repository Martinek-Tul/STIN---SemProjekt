package service;
import model.ExchangeRateResponseDate;
import model.UserSettings;
import model.ExchangeRateResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class ExchangeRateService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ExchangeRateResponse getCurrentRates(UserSettings settings){
        String url = "https://api.frankfurter.app/latest?base="
                +settings.getBaseCurrency()
                +"&symbols="
                +String.join(",", settings.getSelectedCurrencies());
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);
        if (response == null || response.getRates() == null) {
            throw new RuntimeException("Nepodařilo se načíst kurzy z API");
        }
        return response;
    }

    public ExchangeRateResponseDate getCurrentRatesDates(UserSettings settings, String dateFrom, String dateTo){
        String url = "https://api.frankfurter.app/"
                +dateFrom + ".."+dateTo
                +"?base="
                +settings.getBaseCurrency()
                +"&symbols="
                +String.join(",", settings.getSelectedCurrencies());
        System.out.println("URL: " + url);
        ExchangeRateResponseDate response = restTemplate.getForObject(url, ExchangeRateResponseDate.class);
        if (response == null || response.getRates() == null) {
            throw new RuntimeException("Nepodařilo se načíst kurzy z API");
        }
        return response;
    }
}

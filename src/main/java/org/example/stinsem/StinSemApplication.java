package org.example.stinsem;

import model.ExchangeRateResponse;
import model.ExchangeRateResponseDate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import model.UserSettings;
import service.CurrencyAnalyzer;
import service.ExchangeRateService;

import java.util.List;
import java.util.Map;

@SpringBootApplication()
public class StinSemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StinSemApplication.class, args);


        List<String> listik = List.of("AUD", "CAD", "CHF");
        UserSettings us = new UserSettings("CZK", listik);

        ExchangeRateService service = new ExchangeRateService();
        CurrencyAnalyzer analyzer = new CurrencyAnalyzer();

// aktuální kurzy
        ExchangeRateResponse response = service.getCurrentRates(us);
        System.out.println("Kurzy: " + response.getRates());

        Map.Entry<String, Double> strongest = analyzer.findStrongest(response);
        System.out.println("Nejsilnejsi: " + strongest.getKey() + " = " + strongest.getValue());

        Map.Entry<String, Double> weakest = analyzer.findWeakest(response);
        System.out.println("Nejslabsi: " + weakest.getKey() + " = " + weakest.getValue());

// historická data
        ExchangeRateResponseDate responseDate = service.getCurrentRatesDates(us, "2025-01-01", "2025-01-10");
        Map<String, Double> averages = analyzer.calculateAvarage(responseDate);
        System.out.println("Prumery: " + averages);

    }

}

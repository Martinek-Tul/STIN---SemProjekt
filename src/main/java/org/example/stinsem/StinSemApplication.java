package org.example.stinsem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import model.UserSettings;
import service.ExchangeRateService;

import java.util.List;
import java.util.Map;

@SpringBootApplication()
public class StinSemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StinSemApplication.class, args);
        List<String> listik = List.of(
                "AUD", "CAD", "CHF");
        UserSettings us = new UserSettings("CZK", listik);

        ExchangeRateService service = new ExchangeRateService();
        Map<String, Double> map = service.getCurrentRates(us);

        System.out.println(map);

    }

}

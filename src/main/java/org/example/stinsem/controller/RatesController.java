package org.example.stinsem.controller;

import org.example.stinsem.model.ExchangeRateResponse;
import org.example.stinsem.model.ExchangeRateResponseDate;
import org.example.stinsem.model.UserSettings;
import org.springframework.web.bind.annotation.*;
import org.example.stinsem.service.CurrencyAnalyzer;
import org.example.stinsem.service.ExchangeRateService;
import org.example.stinsem.service.UserSettingsService;

import java.util.List;
import java.util.Map;

@RestController
    @RequestMapping("/api")
    public class RatesController {
    private final ExchangeRateService exchangeRateService = new ExchangeRateService();
    private final CurrencyAnalyzer currencyAnalyzer = new CurrencyAnalyzer();
    private final UserSettingsService userSettingsService = new UserSettingsService();


    @GetMapping("/rates")
    public Map<String, Double> getRates(
            @RequestParam String base,
            @RequestParam List<String> symbols) {

        UserSettings settings = new UserSettings(base, symbols);
        ExchangeRateResponse response = exchangeRateService.getCurrentRates(settings);
        return response.getRates();
    }

    @GetMapping("/date")
    public Map<String,Double> getHistoryRates(
            @RequestParam String dateFrom,
            @RequestParam String dateTo,
            @RequestParam String base,
            @RequestParam List<String> symbols){
        UserSettings settings = new UserSettings(base, symbols);
        ExchangeRateResponseDate responseDate = exchangeRateService.getCurrentRatesDates(settings, dateFrom, dateTo);
        return currencyAnalyzer.calculateAvarage(responseDate);
    }

    @GetMapping("/strongest")
    public Map.Entry<String, Double> getStrongest(
            @RequestParam String base,
            @RequestParam List<String> symbols){
        UserSettings settings = new UserSettings(base,symbols);
        ExchangeRateResponse response = exchangeRateService.getCurrentRates(settings);
        return currencyAnalyzer.findStrongest(response);
    }

    @GetMapping("/weakest")
    public Map.Entry<String, Double> getWeakest(
            @RequestParam String base,
            @RequestParam List<String> symbols){
        UserSettings settings = new UserSettings(base,symbols);
        ExchangeRateResponse response = exchangeRateService.getCurrentRates(settings);
        return currencyAnalyzer.findWeakest(response);
    }

    @GetMapping("/settings")
    public UserSettings getSettings() throws Exception {
        return userSettingsService.loadSettings();
    }

    @GetMapping("/settings/save")
    public void saveSettings(@RequestParam String base,
                             @RequestParam List<String> symbols)throws Exception{
        UserSettings settings = new UserSettings(base, symbols);
        userSettingsService.saveSettings(settings);
    }
}

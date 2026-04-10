package org.example.stinsem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.stinsem.model.UserSettings;

import java.io.*;
import java.util.List;

public class UserSettingsService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("settings.json");

    /*public UserSettings loadSettings() throws Exception {
        if (!file.exists()) {
            return new UserSettings("EUR", List.of());
        }
        return mapper.readValue(file, UserSettings.class);
    }

    public void saveSettings(UserSettings settings) throws Exception {
        mapper.writeValue(file, settings);
    }*/

    public UserSettings loadSettings(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("settings.json"));
            String baseCurrency = reader.readLine();
            String symbolsLine = reader.readLine();
            reader.close();

            List<String> selectedCurrencies = List.of(symbolsLine.split(","));
            return new UserSettings(baseCurrency, selectedCurrencies);
        } catch (FileNotFoundException e) {
            return new UserSettings("EUR", List.of());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void saveSettings(UserSettings settings){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("settings.json"));
            writer.write(settings.getBaseCurrency());
            writer.newLine();
            writer.write(String.join(",", settings.getSelectedCurrencies()));
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

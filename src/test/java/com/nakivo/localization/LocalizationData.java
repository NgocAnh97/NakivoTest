package com.nakivo.localization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class LocalizationData {
    public static Map<String, String> getTranslations(String langCode) {
        try {
            File file = new File("src/test/resources/i18n/" + langCode + ".json");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to load language file for: " + langCode);
        }
    }
}

package com.example.testtask3.services;

import com.example.testtask3.repositories.VacancyRepository;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class Base64Encode {
    public static String getUrlFromList(List<String> jobFunctionsList) {
        // Створюємо JSON-рядок зі списку значень
        String jsonString = "{\"job_functions\":" + jobFunctionsList + "}";

        // Кодуємо JSON-рядок Base64
        String base64EncodedString = Base64.getUrlEncoder().encodeToString(jsonString.getBytes(StandardCharsets.UTF_8));

        // Кодуємо у форматі URL || Виводимо результат
        return "https://jobs.techstars.com/jobs?filter=" + URLEncoder.encode(base64EncodedString, StandardCharsets.UTF_8);
    }

    public static String decode(String url) {
        // Декодуємо URL та замінюємо символи знака кодування URL
        String decodedString = URLDecoder.decode(url, StandardCharsets.UTF_8);

        // Декодуємо Base64url
        byte[] decodedBytes = Base64.getUrlDecoder().decode(decodedString);

        // Виводимо результат
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}

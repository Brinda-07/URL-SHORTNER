package com.example.urlshortner.service;

import com.example.urlshortner.model.URL;
import com.example.urlshortner.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private Repo repo;

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;

    public String shortenUrl(String originalUrl) {
        String shortCode = generateShortCode();

        // Ensure shortCode is unique
        while (repo.findByShortCode(shortCode).isPresent()) {
            shortCode = generateShortCode();
        }

        // ✅ Use the correct constructor for URL
        URL url = new URL(originalUrl, shortCode);
        repo.save(url);

        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        Optional<URL> urlOptional = repo.findByShortCode(shortCode);
        return urlOptional.map(URL::getOriginalUrl).orElse(null);
    }

    private String generateShortCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}

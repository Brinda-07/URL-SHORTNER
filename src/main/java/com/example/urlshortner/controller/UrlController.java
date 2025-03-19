package com.example.urlshortner.controller;

import com.example.urlshortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("originalUrl");
        String shortCode = urlService.shortenUrl(originalUrl);

        // Construct the full shortened URL
        String shortUrl = "http://localhost:8080/api/" + shortCode;

        return ResponseEntity.ok(shortUrl);
    }


    @GetMapping("/{shortCode}")
    public String getOriginalUrl(@PathVariable String shortCode) {
        return urlService.getOriginalUrl(shortCode);
    }
}

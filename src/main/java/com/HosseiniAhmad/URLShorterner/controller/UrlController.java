package com.HosseiniAhmad.URLShorterner.controller;

import com.HosseiniAhmad.URLShorterner.model.url.Url;
import com.HosseiniAhmad.URLShorterner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    // Метод для сокращения длинного URL

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String longUrl) {
        String shortUrl = urlService.shortenUrl(longUrl);
        return ResponseEntity.ok(shortUrl);
    }

    // Метод для получения длинного URL по короткому

    @GetMapping("/expand/{shortUrl}")
    public ResponseEntity<String> expandUrl(@PathVariable String shortUrl) {
        String longUrl = urlService.getLongUrl(shortUrl);
        if (longUrl != null) {
            return ResponseEntity.ok(longUrl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Метод для получения всех сокращенных URL

    @GetMapping("/all")
    public ResponseEntity<List<Url>> getAllUrls() {
        List<Url> urls = urlService.getAllUrls();
        return ResponseEntity.ok(urls);
    }

    // Метод для удаления сокращенного URL

    @DeleteMapping("/delete/{shortUrl}")
    public ResponseEntity<String> deleteUrl(@PathVariable String shortUrl) {
        urlService.deleteUrl(shortUrl);
        return ResponseEntity.ok("Url deleted successfully");
    }

    // Метод для увеличения счетчика кликов по сокращенному URL

    @PostMapping("/click/{shortUrl}")
    public ResponseEntity<String> incrementClicks(@PathVariable String shortUrl) {
        urlService.incrementClicks(shortUrl);
        return ResponseEntity.ok("Clicks incremented successfully");
    }
}

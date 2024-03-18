package com.HosseiniAhmad.URLShorterner.controller;

import com.HosseiniAhmad.URLShorterner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String longUrl) {
        return urlService.shortenUrl(longUrl);
    }

    @GetMapping("/{shortUrl}")
    public String redirect(@PathVariable String shortUrl) {
        return urlService.getLongUrl(shortUrl);
    }
}

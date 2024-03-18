package com.HosseiniAhmad.URLShorterner.service;


import com.HosseiniAhmad.URLShorterner.model.url.Url;
import com.HosseiniAhmad.URLShorterner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
public class UrlService { //будет отвечать за создание и управление короткими URL
    @Autowired
    private UrlRepository urlRepository;

    private static final String BASE_URL = "http://google.com/";

    public String shortenUrl(String longUrl) {
        String shortUrl = BASE_URL + UUID.randomUUID().toString().substring(0, 6); // Генерация короткого URL на основе UUID
        urlRepository.save(new Url(longUrl, shortUrl)); // Сохраняем соответствие в базе данных
        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        return (url != null) ? url.getLongUrl() : null; // Возвращаем длинный URL, если короткий URL найден
    }
    public boolean isValidUrl(String url) {
        try {
            // Пытаемся создать объект URL из переданной строки
            new URL(url).toURI();
            // Если URL валиден, возвращаем true
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            // Если произошла ошибка при создании URL, значит URL невалидный
            return false;
        }
    }
    public List<Url> getAllUrls() {
        return urlRepository.findAll(); // Возвращаем список всех сокращенных ссылок
    }

    public void deleteUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        if (url != null) {
            urlRepository.delete(url); // Удаляем ссылку из базы данных
        }
    }

}

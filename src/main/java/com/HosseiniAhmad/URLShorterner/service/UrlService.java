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
  // Внедряем зависимость UrlRepository
    @Autowired
    private UrlRepository urlRepository;
    // Базовый URL для сокращенных ссылок

    private static final String BASE_URL = "http://google.com/";
    // Метод для создания короткой ссылки

    public String shortenUrl(String longUrl) {
        String shortUrl = BASE_URL + UUID.randomUUID().toString().substring(0, 6); // Генерация короткого URL на основе UUID
        urlRepository.save(new Url(null,longUrl, shortUrl,0)); // Сохраняем соответствие в базе данных
        return shortUrl;
    }

    // Метод для получения длинной ссылки по короткой

    public String getLongUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        return (url != null) ? url.getLongUrl() : null; // Возвращаем длинный URL, если короткий URL найден
    }

    // Метод для проверки валидности URL

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
    // Метод для получения всех сокращенных ссылок

    public List<Url> getAllUrls() {
        return urlRepository.findAll(); // Возвращаем список всех сокращенных ссылок
    }
    // Метод для удаления сокращенной ссылки

    public void deleteUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        if (url != null) {
            urlRepository.delete(url); // Удаляем ссылку из базы данных
        }
    }
    // Метод для увеличения счетчика переходов по ссылке

    public void incrementClicks(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        if (url != null) {
            url.setClicks(url.getClicks() + 1); // Увеличиваем счетчик переходов на 1
            urlRepository.save(url); // Сохраняем обновленную информацию в базе данных
        }
    }

}

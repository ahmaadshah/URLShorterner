package com.HosseiniAhmad.URLShorterner;


import com.HosseiniAhmad.URLShorterner.model.url.Url;
import com.HosseiniAhmad.URLShorterner.repository.UrlRepository;
import com.HosseiniAhmad.URLShorterner.service.UrlService;
import org.junit.Test;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UrlServiceTest {

    @MockBean
    private UrlRepository urlRepository;

    @Autowired
    private UrlService urlService;

    @Test
    public void testGetLongUrl() {
        // Подготовка
        String shortUrl = "http://localhost:8080/abc123";
        String expectedLongUrl = "https://example.com";
        Url expectedUrl = new Url(null,expectedLongUrl, shortUrl,0);

        // Мокируем метод репозитория, чтобы он возвращал Optional, содержащий ожидаемый Url
        when(urlRepository.findByShortUrl(any(String.class))).thenReturn(Optional.ofNullable(new Url("example.com", "short")));

        // Вызываем тестируемый метод
        String actualLongUrl = urlService.getLongUrl(shortUrl);

        // Проверка результата
        assertEquals(expectedLongUrl, actualLongUrl);
    }
}
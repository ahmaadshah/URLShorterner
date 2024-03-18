package com.HosseiniAhmad.URLShorterner.repository;

import com.HosseiniAhmad.URLShorterner.model.url.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, String> {
    Url findByShortUrl(String shortUrl);
}

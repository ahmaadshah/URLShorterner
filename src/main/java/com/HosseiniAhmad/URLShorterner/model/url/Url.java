package com.HosseiniAhmad.URLShorterner.model.url;

import com.HosseiniAhmad.URLShorterner.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table

public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // тип Long используется для первичного ключа
    private String longUrl;
    private String shortUrl;
    private int clicks; // Поле для счетчика переходов


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;



}


package com.HosseiniAhmad.URLShorterner.model.url;

import com.HosseiniAhmad.URLShorterner.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Url {
    @Id
    private String id;
    private String longUrl;
    private String shortUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
}

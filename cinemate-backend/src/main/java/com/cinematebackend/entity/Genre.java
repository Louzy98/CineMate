package com.cinematebackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="genres")
@Getter
@Setter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Long tmdbId;
    private String name;


}

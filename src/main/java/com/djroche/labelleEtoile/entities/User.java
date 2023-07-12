package com.djroche.labelleEtoile.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private  String username;

    @Column (nullable = false, unique = true)
    private  String password;

    @Column (nullable = false)
    private boolean admin;
}

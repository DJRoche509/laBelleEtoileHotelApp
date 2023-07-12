package com.djroche.labelleEtoile.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private  String username;

    @Column (name = "password", nullable = false, unique = true)
    private  String password;

    @Column (name = "admin",nullable = false)
    private boolean admin;
}

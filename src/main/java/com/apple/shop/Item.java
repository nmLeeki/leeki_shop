package com.apple.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    public Long id;
    public String title;
    public Integer price;
}

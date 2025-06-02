package com.apple.shop.item.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    public Long id;
    private String title;
    private Integer price;
    private String username;
}

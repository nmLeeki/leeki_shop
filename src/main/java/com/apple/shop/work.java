package com.apple.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class work {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    public Long id;
    public String title;
    public Date date;
}

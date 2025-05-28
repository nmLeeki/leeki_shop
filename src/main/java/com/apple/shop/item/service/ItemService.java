package com.apple.shop.item.service;

import com.apple.shop.item.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();
    Item save(Item item);
    Item findById(Long id);
    Item update(Long id, Item item);
    void delete(Long id);
}
package com.apple.shop;

import java.util.List;

public interface ItemService {
    List<Item> findAll();
    Item save(Item item);
    Item findById(Long id);
}
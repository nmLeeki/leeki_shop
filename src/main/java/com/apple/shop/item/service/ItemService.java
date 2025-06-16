package com.apple.shop.item.service;

import com.apple.shop.item.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {
    Page<Item> findAll(Pageable pageable);
    Item save(Item item);
    Item findById(Long id);
    Item update(Long id, Item item);

    void delete(Long id);
}
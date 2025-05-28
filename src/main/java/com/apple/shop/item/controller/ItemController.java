package com.apple.shop.item.controller;

import com.apple.shop.item.service.ItemService;
import com.apple.shop.item.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/list")
    public List<Item> list() {
        return itemService.findAll();
    }

    @PostMapping("/add")
    public Item writePost(@RequestBody Item item) {
        return itemService.save(item);
    }

    @GetMapping("/detail/{id}")
    public Item getItemDetail(@PathVariable Long id) {
        return itemService.findById(id);
    }
    // @PutMapping("/update/{id}")
    @PostMapping("/update/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
      return itemService.update(id, item);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.delete(id);
    }

}
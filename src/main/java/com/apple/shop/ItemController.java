package com.apple.shop;

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
}
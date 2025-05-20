package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/list")
    @ResponseBody
    public List<Item> list() {
        return itemRepository.findAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public Item writePost(@RequestBody Item item) {
        if(item.getTitle() == null || item.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        } else {
            itemRepository.save(item);
            return item;
        }

    }
}


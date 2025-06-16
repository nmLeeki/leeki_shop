package com.apple.shop.item.controller;

import com.apple.shop.item.service.ItemService;
import com.apple.shop.item.model.Item;
import com.apple.shop.item.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final S3Service s3Service;

    @PostMapping("/list")
    public Page<Item> list(@RequestBody Map<String, Integer> params) {
        int page = params.getOrDefault("page", 0);
        int size = params.getOrDefault("size", 5);
        return itemService.findAll(PageRequest.of(page, size));
    }
    @PostMapping("/presignedURL")
    public Map<String, String> getPresignedURL(@RequestBody Map<String, String> params) {
        String fileName = params.get("fileName");
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be empty");
        }
        String url = s3Service.createPresignedUrl("test/" + fileName);
        return Map.of("url", url);
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
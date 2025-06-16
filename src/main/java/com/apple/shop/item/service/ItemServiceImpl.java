package com.apple.shop.item.service;

import com.apple.shop.item.model.Item;
import com.apple.shop.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Override
    public Item save(Item item) {
        if(item.getTitle() == null || item.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("현재 인증 정보 아이템 추가할때: " + auth);
        item.setUsername(auth.getName());
        return itemRepository.save(item);
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다: " + id));
    }
    @Override
    public Item update(Long id, Item item) {
        Item existingItem = findById(id);
        if(item.getTitle() != null && !item.getTitle().isEmpty()) {
            existingItem.setTitle(item.getTitle());
        }
        if(item.getPrice() != null) {
            existingItem.setPrice(item.getPrice());
        }
        return itemRepository.save(existingItem);
    }
    @Override
    public void delete(Long id) {
        if(!itemRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 아이템이 없습니다: " + id);
        }
        itemRepository.deleteById(id);
    }
}
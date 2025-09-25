package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @PostMapping
    public Item createItem() {
        return new Item(UUID.randomUUID(), "Item 1");
    }

    @PostMapping("/null-id")
    public Item createItemWithNullId() {
        return new Item(null, "Item 1");
    }
}

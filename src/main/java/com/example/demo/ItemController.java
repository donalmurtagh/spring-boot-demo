package com.example.demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
@PreAuthorize("hasAuthority('FOO')")
public class ItemController {

    @GetMapping
    public Item getItem() {
        return new Item("a lovely thing");
    }
}

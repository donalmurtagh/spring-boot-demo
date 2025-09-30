package com.example.demo;

import jakarta.validation.constraints.NotBlank;

public record UpdateRequest(
    @NotBlank
    String name
) {
}

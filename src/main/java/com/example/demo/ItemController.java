package com.example.demo;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @PutMapping
    public ApiErrorResponse updateItem() {
        var apiError = new ApiErrorResponse("code", "message");
        var fieldError = new ApiFieldError("fieldCode", "fieldProperty", "fieldMessage", "path");
        apiError.addFieldError(fieldError);
        return apiError;
    }
}

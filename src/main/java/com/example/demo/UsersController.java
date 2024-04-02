package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/success")
    public void success() {
    }

    @GetMapping("/errorInController")
    public void errorInController() {
        throw new RuntimeException("error in controller");
    }

    // ThrowErrorFilter is applied to this URL, see ServletFilterConfiguration
    @GetMapping("/errorInFilter")
    public void errorInFilter() {

    }
}

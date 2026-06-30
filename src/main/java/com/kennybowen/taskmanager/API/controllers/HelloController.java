package com.kennybowen.taskmanager.API.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/Hello")
public class HelloController {

    @GetMapping
    public String Hello() {
        return "Say hello";
    }
}

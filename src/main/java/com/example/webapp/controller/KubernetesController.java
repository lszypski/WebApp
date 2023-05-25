package com.example.webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class KubernetesController {

    @GetMapping("/kubernetes")
    public String getPodName() {
        return System.getenv("HOSTNAME");
    }
}

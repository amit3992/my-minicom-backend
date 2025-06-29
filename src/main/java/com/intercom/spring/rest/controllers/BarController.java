package com.intercom.spring.rest.controllers;

import java.util.Map;
import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class BarController {

    @PostMapping("/bar")
    public Map verify() {
        log.info("bar");
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}
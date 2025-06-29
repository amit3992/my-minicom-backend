package com.intercom.spring.rest.controllers;

import java.util.Map;
import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FooController {

    @PostMapping("/foo")
    public Map verify() {
        log.info("foo");
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

}
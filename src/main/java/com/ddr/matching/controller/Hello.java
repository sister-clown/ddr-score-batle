package com.ddr.matching.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

@RestController
@ResponseBody
public class Hello {

    @RequestMapping("/")
    public Resource index() {
        Resource resource = new Resource();
        return resource;
    }

    public class Resource implements Serializable {
        public String value = "Greetings from Spring Boot! /";
        public String test = "test";
        public String nulll = null;
    }
}
package com.janja.myTwitter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyTwitterController {

    @GetMapping
    @RequestMapping(value = "/myTwitter")
    public String getMain() {

        return "myTwitter";
    }

    @GetMapping
    @RequestMapping(value = "/myTwitter/{username}")
    public String getMainUser() {
        return "myTwitter";
    }
}

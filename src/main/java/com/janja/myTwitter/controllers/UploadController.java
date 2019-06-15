package com.janja.myTwitter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/avatarUpload")
public class UploadController {

    @GetMapping
    public String getAvatarUpload() {
        return "avatarUpload";
    }
}

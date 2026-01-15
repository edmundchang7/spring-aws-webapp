package com.example.springawswebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReverseController {

    @GetMapping("/reverse")
    public String reverse() {
        return "reverse";
    }

    @PostMapping("/reverse")
    public String reverseString(@RequestParam("text") String text, Model model) {
        String reversedText = new StringBuilder(text).reverse().toString();
        model.addAttribute("originalText", text);
        model.addAttribute("reversedText", reversedText);
        return "reverse";
    }
}
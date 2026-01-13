package com.example.springawswebapp.controller;

import com.example.springawswebapp.service.TranslateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class TranslateController {

    private final TranslateService translateService;

    public TranslateController(TranslateService translateService) {
        this.translateService = translateService;
    }

    @GetMapping("/translate")
    public String showTranslatePage(Model model) {
        model.addAttribute("sourceText", "");
        model.addAttribute("translatedText", "");
        model.addAttribute("targetLanguage", "es"); // Default to Spanish
        return "translate";
    }

    @PostMapping("/translate")
    public String translateText(@RequestParam("sourceText") String sourceText,
                                @RequestParam("targetLanguage") String targetLanguage,
                                Model model) {
        String translatedText = translateService.translateText(sourceText, targetLanguage);

        model.addAttribute("sourceText", sourceText);
        model.addAttribute("translatedText", translatedText);
        model.addAttribute("targetLanguage", targetLanguage);
        return "translate";
    }
}

package com.example.springawswebapp.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.translate.TranslateClient;
import software.amazon.awssdk.services.translate.model.TranslateTextRequest;
import software.amazon.awssdk.services.translate.model.TranslateTextResponse;

@Service
public class TranslateService {

    private final TranslateClient translateClient;

    public TranslateService() {
        this.translateClient = TranslateClient.builder()
                .region(Region.US_EAST_2)
                .build();
    }

    public String translateText(String sourceText, String targetLanguage) {
        TranslateTextRequest request = TranslateTextRequest.builder()
                .sourceLanguageCode("auto") // Let Amazon Translate detect the source language
                .targetLanguageCode(targetLanguage)
                .text(sourceText)
                .build();

        TranslateTextResponse response = translateClient.translateText(request);
        return response.translatedText();
    }
}

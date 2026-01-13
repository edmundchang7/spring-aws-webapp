package com.example.springawswebapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReverseControllerTest {

    @InjectMocks
    private ReverseController reverseController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReverseString_normalCase() {
        String originalText = "hello";
        String expectedReversedText = "olleh";

        String viewName = reverseController.reverseString(originalText, model);

        assertEquals("reverse", viewName);
        verify(model, times(1)).addAttribute("originalText", originalText);
        verify(model, times(1)).addAttribute("reversedText", expectedReversedText);
    }

    @Test
    void testReverseString_emptyString() {
        String originalText = "";
        String expectedReversedText = "";

        String viewName = reverseController.reverseString(originalText, model);

        assertEquals("reverse", viewName);
        verify(model, times(1)).addAttribute("originalText", originalText);
        verify(model, times(1)).addAttribute("reversedText", expectedReversedText);
    }

    @Test
    void testReverseString_singleCharacter() {
        String originalText = "a";
        String expectedReversedText = "a";

        String viewName = reverseController.reverseString(originalText, model);

        assertEquals("reverse", viewName);
        verify(model, times(1)).addAttribute("originalText", originalText);
        verify(model, times(1)).addAttribute("reversedText", expectedReversedText);
    }

    @Test
    void testReverseString_withSpaces() {
        String originalText = "hello world";
        String expectedReversedText = "dlrow olleh";

        String viewName = reverseController.reverseString(originalText, model);

        assertEquals("reverse", viewName);
        verify(model, times(1)).addAttribute("originalText", originalText);
        verify(model, times(1)).addAttribute("reversedText", expectedReversedText);
    }

    @Test
    void testReverseString_withSpecialCharacters() {
        String originalText = "!@#$%";
        String expectedReversedText = "%$#@!";

        String viewName = reverseController.reverseString(originalText, model);

        assertEquals("reverse", viewName);
        verify(model, times(1)).addAttribute("originalText", originalText);
        verify(model, times(1)).addAttribute("reversedText", expectedReversedText);
    }

    @Test
    void testReverseString_palindrome() {
        String originalText = "madam";
        String expectedReversedText = "madam";

        String viewName = reverseController.reverseString(originalText, model);

        assertEquals("reverse", viewName);
        verify(model, times(1)).addAttribute("originalText", originalText);
        verify(model, times(1)).addAttribute("reversedText", expectedReversedText);
    }
}

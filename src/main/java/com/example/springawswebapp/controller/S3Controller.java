package com.example.springawswebapp.controller;

import com.example.springawswebapp.service.S3Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/s3")
    public String listS3Buckets(Model model) {
        List<String> bucketNames = s3Service.listBuckets();
        if (bucketNames.size() == 1 && bucketNames.get(0).startsWith("Error:")) {
            model.addAttribute("error", bucketNames.get(0));
            model.addAttribute("bucketNames", java.util.Collections.emptyList());
        } else {
            model.addAttribute("bucketNames", bucketNames);
        }
        return "s3";
    }

    @GetMapping("/s3/upload")
    public String showUploadPage(Model model) {
        List<String> bucketNames = s3Service.listBuckets();
        model.addAttribute("bucketNames", bucketNames);
        return "s3-upload";
    }

    @PostMapping("/s3/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("bucketName") String bucketName, RedirectAttributes redirectAttributes) {
        try {
            s3Service.uploadFile(bucketName, file.getOriginalFilename(), file.getInputStream());
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "' to '" + bucketName + "'!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to upload file: " + e.getMessage());
        }
        return "redirect:/s3";
    }
}

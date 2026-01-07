package com.example.springawswebapp.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3Service {

    private final S3Client s3Client;

    public S3Service() {
        // You can specify a region, or rely on the default credential provider chain
        // which often includes region from environment variables or config files.
        this.s3Client = S3Client.builder()
                // .region(Region.US_EAST_1) // Example: explicitly set region
                .build();
    }

    public List<String> listBuckets() {
        try {
            ListBucketsResponse response = s3Client.listBuckets();
            return response.buckets().stream()
                    .map(Bucket::name)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error listing S3 buckets: " + e.getMessage());
            return Collections.singletonList("Error: Could not list buckets. Check AWS credentials and region.");
        }
    }
}

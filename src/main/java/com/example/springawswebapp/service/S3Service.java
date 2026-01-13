package com.example.springawswebapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(S3Service.class);

    private final S3Client s3Client;

    public S3Service(@Value("${AWS_REGION}") String awsRegion) {
        // You can specify a region, or rely on the default credential provider chain
        // which often includes region from environment variables or config files.
        this.s3Client = S3Client.builder()
                .region(Region.of(awsRegion)) // Example: explicitly set region
                .build();
    }

    public List<String> listBuckets() {
        try {
            ListBucketsResponse response = s3Client.listBuckets();
            return response.buckets().stream()
                    .map(Bucket::name)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Error listing S3 buckets", e);
            return Collections.singletonList("Error: " + e.getMessage());
        }
    }
}

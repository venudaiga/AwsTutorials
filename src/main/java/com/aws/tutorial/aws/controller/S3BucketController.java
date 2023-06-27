package com.aws.tutorial.aws.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.aws.tutorial.aws.service.S3Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
public class S3BucketController {

    @Autowired
    S3Bucket s3Bucket;

    @PostMapping("/create/{bucketName}")
    public Bucket createBucket(@PathVariable String bucketName) {
        System.out.println("Inside Create Bucket");
        return s3Bucket.createBucket(bucketName);
    }

    @DeleteMapping("/delete/{bucketName}/{key}")
    public void deleteBucket(@PathVariable String bucketName,@PathVariable String key) {
        System.out.println("Inside Delete Bucket");
        s3Bucket.deleteBucket(bucketName,key);
    }

    @GetMapping("/bucket/{bucketName}")
    public String getBucketName(@PathVariable String bucketName) {
        System.out.println("Inside Get Bucket");
        return s3Bucket.getBuckets(bucketName);
    }

    @PostMapping("/upload/{bucketName}")
    public PutObjectResult fileUpload(@RequestParam MultipartFile file, @PathVariable String bucketName) {
        System.out.println("Inside File Upload ");
        return s3Bucket.uploadFile(file, bucketName);
    }

    @PostMapping("/download/{bucketName}/{key}")
    public ResponseEntity<byte[]> fileDownload(@PathVariable String bucketName, @PathVariable String key) {
        System.out.println("Inside File Download ");
        return ResponseEntity.ok().body(s3Bucket.downloadFile(bucketName, key));
    }
}
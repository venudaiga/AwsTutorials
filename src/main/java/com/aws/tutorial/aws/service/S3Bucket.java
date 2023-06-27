package com.aws.tutorial.aws.service;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

public interface S3Bucket {
     String getBuckets(String bucketName);
     void deleteBucket(String bucketName,String key);
     Bucket createBucket(String bucketName);
    PutObjectResult uploadFile(MultipartFile file, String bucketName);
    byte[] downloadFile(String bucketName, String key);
}

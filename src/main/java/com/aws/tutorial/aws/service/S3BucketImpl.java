package com.aws.tutorial.aws.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3BucketImpl implements S3Bucket {

    @Autowired
    AmazonS3 amazonS3;

    @Override
    public String getBuckets(String bucketName) {
        //List<Bucket> bucketList=amazonS3.listBuckets();
        ObjectListing objectListing = amazonS3.listObjects(bucketName);
        return objectListing.getBucketName();
    }

    @Override
    public void deleteBucket(String bucketName,String key) {
        // amazonS3.deleteBucket(bucketName);
        amazonS3.deleteObject(bucketName,key);
    }

    @Override
    public Bucket createBucket(String bucketName) {
        return amazonS3.createBucket(bucketName);
    }

    @Override
    public PutObjectResult uploadFile(MultipartFile file, String bucketName) {
        PutObjectResult result;
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());
            System.out.println("FIle Name " + file.getOriginalFilename() + " Type " + file.getContentType());
            result = amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), objectMetadata);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public byte[] downloadFile(String bucketName, String key) {
        S3Object s3Object = amazonS3.getObject(bucketName, key);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        byte[] op;
        try {
            op = IOUtils.toByteArray(inputStream);
            s3Object.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return op;
    }
}
package com.aws.tutorial.aws.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

public AWSCredentials awsCredentials(){
    AWSCredentials awsCredentials=
            new BasicAWSCredentials("XXXXXXX","xxxxxxxxx");
    return awsCredentials;
}
@Bean
    public AmazonS3 amazonS3(){
    AmazonS3 s3Client= AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
            .withRegion(Regions.AP_SOUTH_1).build();
    return s3Client;
}

@Bean
    public AmazonSNS amazonSNSClient(){
    return AmazonSNSClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
            .withRegion(Regions.AP_SOUTH_1).build();
}
//SNS Config
//SQS
//MQ
// Lambda funcation

}

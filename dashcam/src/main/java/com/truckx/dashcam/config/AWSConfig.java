package com.truckx.dashcam.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {
    @Value("${aws.s3_bucket.access_key}")
    private String s3AccessKey;

    @Value("${aws.s3_bucket.secret_key}")
    private String s3SecretKey;

    @Value("${aws.s3_bucket.region}")
    private String s3RegionCode;

    @Bean(name = "s3_client")
    public AmazonS3 getAmazonS3() {
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(getAwsStaticCredentialsProvider())
                .withRegion(s3RegionCode)
                .build();
        return amazonS3;
    }

    private AWSStaticCredentialsProvider getAwsStaticCredentialsProvider() {
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(s3AccessKey, s3SecretKey));
        return awsStaticCredentialsProvider;
    }

    @Bean
    public TransferManager getTransferManager(@Qualifier("s3_client") AmazonS3 amazonS3Encryption) {
        TransferManager transferManager = TransferManagerBuilder.standard()
                .withS3Client(amazonS3Encryption)
                .build();
        return transferManager;
    }
}

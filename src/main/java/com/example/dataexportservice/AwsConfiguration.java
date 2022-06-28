package com.example.dataexportservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfiguration {
    @Value("${Cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${Cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(this.accessKey, this.secretKey);
        return StaticCredentialsProvider.create(credentials);
    }

    @Bean
    public S3Client s3Client(final AwsCredentialsProvider awsCredentialsProvider) {
        return S3Client.builder()
                .region(Region.AP_SOUTHEAST_2)
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }

}

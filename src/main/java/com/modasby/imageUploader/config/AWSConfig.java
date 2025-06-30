package com.modasby.imageUploader.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
public class AWSConfig {

    @Bean
    public S3Client s3Client(
            @Value("${aws.s3.accessKeyId}") String accessKeyId,
            @Value("${aws.s3.secretAccessKey}") String secretAccessKey,
            @Value("${aws.s3.region:sa-east-1}") String region,
            @Value("${aws.s3.endpoint:http://localhost:9000}") String endpointUrl
    ) {
        return S3Client
                .builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .endpointOverride(URI.create(endpointUrl))
                .forcePathStyle(true)
                .build();
    }

    @Bean
    public S3Presigner s3Presigner(
            @Value("${aws.s3.accessKeyId}") String accessKeyId,
            @Value("${aws.s3.secretAccessKey}") String secretAccessKey,
            @Value("${aws.s3.region:sa-east-1}") String region,
            @Value("${aws.s3.endpoint:http://localhost:9000}") String endpointUrl
    ) {
        return S3Presigner
                .builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .endpointOverride(URI.create(endpointUrl))
                .serviceConfiguration(
                        S3Configuration.builder().pathStyleAccessEnabled(true).build()
                )
                .build();
    }
}

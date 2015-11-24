package com.rst.oauth2.google.config;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by lmorganti on 24/11/15.
 */
@Configuration
@PropertySource("classpath:credential.properties")
public class DynamoDBInstanceConfig {

    @Value("${amazon.credential.accessKey}")
    private String accessKey;

    @Value("${amazon.credential.secretKey}")
    private String secretKey;

    @Bean
    public DynamoDB dynamoDBInstance() {
        return new DynamoDB(new AmazonDynamoDBClient(
                new BasicAWSCredentials(accessKey, secretKey)));
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}

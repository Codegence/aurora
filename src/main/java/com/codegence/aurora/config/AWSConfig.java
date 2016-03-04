package com.codegence.aurora.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


@Configuration
@EnableDynamoDBRepositories(basePackages = "com.codegence.aurora")
public class AWSConfig {

    @Value("${amazon.accessKey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.secretKey}")
    private String amazonAWSSecretKey;
    private Region region = Region.getRegion(Regions.US_EAST_1);

    @Bean

    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
        amazonDynamoDB.setRegion(region);
//        amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        return amazonDynamoDB;
    }

    @Bean
    @DependsOn({"amazonDynamoDB"})
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(amazonDynamoDB(),
                new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES));
    }

    @Bean
    public QueueMessagingTemplate amazonSQS() {
        AmazonSQS amazonSQS = new AmazonSQSClient(amazonAWSCredentials());
        amazonSQS.setRegion(region);
        QueueMessagingTemplate queueMessagingTemplate = new QueueMessagingTemplate(amazonSQS);
        return queueMessagingTemplate;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

}
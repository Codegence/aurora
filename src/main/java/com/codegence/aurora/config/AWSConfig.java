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
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.buffered.AmazonSQSBufferedAsyncClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;

import java.util.ArrayList;
import java.util.List;


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
    public AmazonSQSAsync amazonSQSAsync() {
        AmazonSQSAsyncClient amazonSQSAsyncClient = new AmazonSQSAsyncClient(amazonAWSCredentials());
        amazonSQSAsyncClient.setRegion(region);
        return new AmazonSQSBufferedAsyncClient(amazonSQSAsyncClient);
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }


    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSQSAsync());
        factory.setMaxNumberOfMessages(5);
        factory.setDeleteMessageOnException(false);

        SimpleMessageListenerContainer messageListenerContainer = factory.createSimpleMessageListenerContainer();
        messageListenerContainer.setMessageHandler(queueMessageHandler());
        return messageListenerContainer;
    }

    @Bean
    public QueueMessageHandler queueMessageHandler() {
        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
        factory.setAmazonSqs(amazonSQSAsync());
        QueueMessageHandler messageHandler = factory.createQueueMessageHandler();
        List<HandlerMethodArgumentResolver> list = new ArrayList<>();
        HandlerMethodArgumentResolver resolver = new PayloadArgumentResolver(new MappingJackson2MessageConverter());
        list.add(resolver);
        messageHandler.setArgumentResolvers(list);
        return messageHandler;
    }

}
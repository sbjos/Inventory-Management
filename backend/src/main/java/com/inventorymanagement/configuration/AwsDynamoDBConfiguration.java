package com.inventorymanagement.configuration;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AwsDynamoDBConfiguration {

    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Singleton
    @Provides
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(getDynamoDBClient());
    }

    private AmazonDynamoDB getDynamoDBClient() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(Regions.US_WEST_2)
                .build();
    }
}

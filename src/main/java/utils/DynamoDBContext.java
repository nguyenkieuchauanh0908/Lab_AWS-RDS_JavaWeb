package utils;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public class DynamoDBContext {
    private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .build();

    private static DynamoDB dynamoDB;
    private static DynamoDBMapper dynamoDBMapper;

    public static DynamoDB getDynamoDB() {
        if(dynamoDB == null)
            dynamoDB = new DynamoDB(client);
        return dynamoDB;
    }

    public static DynamoDBMapper getDynamoDBMapper() {
        return new DynamoDBMapper(client);
    }

    public static AmazonDynamoDB getAmazonClient() {return client;}
}

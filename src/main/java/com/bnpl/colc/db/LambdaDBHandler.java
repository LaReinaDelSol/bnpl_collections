package com.bnpl.colc.db;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.event.S3EventNotification;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

public class LambdaDBHandler  implements RequestHandler<S3EventNotification, String> {

	// Instantiate credentials
	final static private String AWS_ACCESS_KEY_ID = "AKIA5PTIVJ2P4DXQQU2E";
	final static private String AWS_SECRET_ACCESS_KEY = "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";
	
	@Override
	public String handleRequest(S3EventNotification input, Context context) {
		
		LambdaLogger logger = context.getLogger();		
		logger.log("<<<<<LambdaDBHandler.handleRequest is Triggered>>>>>");
		
		String tableName = "Cinemas";
		DynamoDbClient ddbclient = DynamoDbClient.builder()
				.region(Region.US_EAST_1).credentialsProvider(StaticCredentialsProvider
						.create(AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)))
				.build();
		
		CallingDynamoDB dbCaller = new CallingDynamoDB();
		QueryResponse res = dbCaller.queryItem(ddbclient, tableName, true);
		
		for (Map<String, AttributeValue> ele : res.items()) {
			for (Map.Entry<String, AttributeValue> entry : ele.entrySet()) {
				logger.log("Attribute : " + entry.getKey() + ", Value : " + entry.getValue());
			}
		}
		
		logger.log("<<<<<LambdaDBHandler.handleRequest is Terminated>>>>>");
		return input.toJson();
	}

}

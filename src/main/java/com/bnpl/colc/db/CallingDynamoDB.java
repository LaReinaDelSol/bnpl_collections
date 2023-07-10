package com.bnpl.colc.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.KeyAttribute;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.CreateTableResponse;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import software.amazon.awssdk.services.dynamodb.model.TableStatus;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemResponse;

public class CallingDynamoDB {

	// Instantiate credentials
	final static private String AWS_ACCESS_KEY_ID = "AKIA5PTIVJ2P4DXQQU2E";
	final static private String AWS_SECRET_ACCESS_KEY = "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";

	public static void main(String[] args) {
	//public void main() {

		// creating a table called Movies in DynamoDB - start
		String tableName = "OP_LoanAccount";

		DynamoDbClient ddbclient = DynamoDbClient.builder()
				// The region is meaningless for local DynamoDb but required for client builder
				// validation
				.region(Region.US_EAST_1).credentialsProvider(StaticCredentialsProvider
						.create(AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)))
				.build();

		//deleteTable(ddbclient, tableName);

		//createTable(ddbclient, tableName);

		//insertItemIntoTable(ddbclient, tableName);

		//queryItem(ddbclient, tableName);

		//listAllTableItems(ddbclient, tableName);
		
		//updateItemWithNewAttribute(ddbclient, tableName);
		
		updateItem(ddbclient, tableName);
	}
	
	public static void updateItem(DynamoDbClient ddbclient, String tableName){
		
		 // Create the credentials
        BasicAWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY);

        // Create the DynamoDB client
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(credentials)
                .withRegion(Regions.US_EAST_1); // Replace with your desired region
		
		 // Create the credentials provider
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY));

     // Create the DynamoDB object
        		// Create the DynamoDB object
                DynamoDB dynamoDB = new DynamoDB(client); // Replace with your desired region


		//AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY));

        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
        		.withRegion(Regions.US_EAST_1)
        		.withCredentials(credentialsProvider).build();

        // Create the DynamoDB object
       // DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        
        // Get the table reference
        Table table = dynamoDB.getTable(tableName);

        // adding new attribute to existing item
		//AttributeUpdate update2= new AttributeUpdate("newAttr").put("222");
		List<AttributeUpdate> attrlist=new ArrayList<AttributeUpdate>();
		AttributeUpdate update1= new AttributeUpdate("voucherCode").put("923847521130");
		attrlist.add(update1);
		//attrlist.add(update2);

		KeyAttribute partitionKey = new KeyAttribute("RefID", "C065789034201");
        KeyAttribute sortKey = new KeyAttribute("RefDate", "1686725555");
        
		UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey(partitionKey, sortKey).withAttributeUpdate(attrlist)
				.withReturnValues(ReturnValue.ALL_NEW);
		UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

		// Check the response.
		System.out.println("Printing item after updating new attribute...");
		System.out.println(outcome.getItem().toJSONPretty());
	}
	
	public static void updateItemWithNewAttribute(DynamoDbClient ddbclient, String tableName) {
		
		// create the key attributes
		HashMap<String,AttributeValue> itemKey = new HashMap<>();
		itemKey.put("RefID", AttributeValue.builder().s("C065789034201").build());
		itemKey.put("RefDate", AttributeValue.builder().n("1686725555").build());		
		
        // create the attribute values
        HashMap<String,AttributeValueUpdate> updatedValues = new HashMap<>();	
        updatedValues.put("voucherCode", AttributeValueUpdate.builder()
            .value(AttributeValue.builder().s("923847521130").build())
            .action(AttributeAction.PUT)
            .build());
               
		// Create the update request
        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(tableName)
                .key(itemKey)
               // .updateExpression("set voucherCode = :923847521130")
                .attributeUpdates(updatedValues)
                .build();

        // Perform the update
        UpdateItemResponse response = ddbclient.updateItem(request);

        // Print the updated item
        System.out.println("Updated item: " + response.attributes());
	}

	public void listAllTableItems(DynamoDbClient ddbclient, String tableName) {
		// Create a ScanRequest instance
		ScanRequest scanRequest = ScanRequest.builder().tableName(tableName).build();

		// Send the ScanRequest and process the results
		ScanResponse scanResponse = ddbclient.scan(scanRequest);

		for (Map<String, AttributeValue> ele : scanResponse.items()) {
			// Process each item in the response
			for (Map.Entry<String, AttributeValue> entry : ele.entrySet()) {
				System.out.println("Attribute : " + entry.getKey() + ", Value : " + entry.getValue());
			}

		}
	}

	public void queryItem(DynamoDbClient ddbclient, String tableName) {
		// Create a QueryRequest instance
		Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
		expressionAttributeValues.put(":pkValue", AttributeValue.builder().s("LoP").build());
		expressionAttributeValues.put(":skValue", AttributeValue.builder().n("2000").build());

		QueryRequest queryRequest = QueryRequest.builder().tableName(tableName)
				.keyConditionExpression("title = :pkValue AND releaseYear = :skValue")
				.expressionAttributeValues(expressionAttributeValues).build();

		// Send the QueryRequest and process the results
		QueryResponse queryResponse = ddbclient.query(queryRequest);

		for (Map<String, AttributeValue> ele : queryResponse.items()) {
			for (Map.Entry<String, AttributeValue> entry : ele.entrySet()) {
				System.out.println("Attribute : " + entry.getKey() + ", Value : " + entry.getValue());
			}
		}
	}
	
	public QueryResponse queryItem(DynamoDbClient ddbclient, String tableName, boolean lambdaFunction) {
		// Create a QueryRequest instance
		Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
		expressionAttributeValues.put(":pkValue", AttributeValue.builder().s("LoP").build());
		expressionAttributeValues.put(":skValue", AttributeValue.builder().n("2000").build());

		QueryRequest queryRequest = QueryRequest.builder().tableName(tableName)
				.keyConditionExpression("title = :pkValue AND releaseYear = :skValue")
				.expressionAttributeValues(expressionAttributeValues).build();

		// Send the QueryRequest and process the results
		QueryResponse queryResponse = ddbclient.query(queryRequest);
		return queryResponse;
	}

	public void insertItemIntoTable(DynamoDbClient ddbclient, String tableName) {
		// Create a PutItemRequest instance
		Map<String, AttributeValue> item = new HashMap<>();
		item.put("title", AttributeValue.builder().s("Lucy").build());
		item.put("releaseYear", AttributeValue.builder().n("2010").build());
		item.put("awarded", AttributeValue.builder().bool(false).build());
		item.put("director", AttributeValue.builder().s("don't know").build());

		// Create a PutItemRequest instance
		PutItemRequest putItemRequest = PutItemRequest.builder().tableName(tableName).item(item).build();

		// Send the PutItem request
		PutItemResponse putItemResponse = ddbclient.putItem(putItemRequest);

		// Access the response properties if needed
		System.out.println("Item inserted successfully." + putItemResponse.responseMetadata());
	}

	public void createTable(DynamoDbClient ddbclient, String tableName) {
		// Create a CreateTableRequest instance
		CreateTableRequest createTabRequest = CreateTableRequest.builder().tableName(tableName).attributeDefinitions(
				AttributeDefinition.builder().attributeName("title").attributeType(ScalarAttributeType.S).build(),
				AttributeDefinition.builder().attributeName("releaseYear").attributeType(ScalarAttributeType.N).build())
				.keySchema(KeySchemaElement.builder().attributeName("title").keyType(KeyType.HASH).build(),
						KeySchemaElement.builder().attributeName("releaseYear").keyType(KeyType.RANGE).build())
				.provisionedThroughput(
						ProvisionedThroughput.builder().readCapacityUnits(5L).writeCapacityUnits(5L).build())
				.build();

		// Send the CreateTable request
		CreateTableResponse response = ddbclient.createTable(createTabRequest);

		boolean tableCreated = isTableActive(ddbclient, tableName);
		while (!tableCreated) {
			try {
				Thread.sleep(5000);
				tableCreated = isTableActive(ddbclient, tableName);
				// If describeTable does not throw an exception, the table still exists
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("table deleted : " + response.responseMetadata());
	}

	public void deleteTable(DynamoDbClient ddbclient, String tableName) {
		DeleteTableRequest delTabRequest = DeleteTableRequest.builder().tableName(tableName).build();
		try {
			ddbclient.deleteTable(delTabRequest);
			boolean tableDeleted = isTableActive(ddbclient, tableName);
			while (tableDeleted) {
				try {
					Thread.sleep(5000);
					tableDeleted = isTableActive(ddbclient, tableName);
					// If describeTable does not throw an exception, the table still exists
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("table deleted");

		} catch (ResourceNotFoundException rnfe) {
			System.out.println(rnfe.getMessage());
		} catch (DynamoDbException dde) {
			System.out.println(dde.getMessage());
		}
	}

	private boolean isTableActive(DynamoDbClient client, String tableName) {
		DescribeTableRequest describeTableRequest = DescribeTableRequest.builder().tableName(tableName).build();

		DescribeTableResponse describeTableResponse = client.describeTable(describeTableRequest);
		TableStatus tableStatus = describeTableResponse.table().tableStatus();

		return TableStatus.ACTIVE.equals(tableStatus);
	}

}

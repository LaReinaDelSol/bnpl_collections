package com.bnpl.colc.db;

import com.bnpl.colc.constant.CollectionsConstants;
import com.bnpl.colc.dto.LoanAccountData;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class DynamoDBProcessor {

	// AWS credentials
	private static final String AWS_ACCESS_KEY_ID = "AKIA5PTIVJ2P4DXQQU2E";
	private static final String AWS_SECRET_ACCESS_KEY = "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";

	private static DynamoDbClient ddbclient = DynamoDbClient.builder().region(Region.US_EAST_1).credentialsProvider(
			StaticCredentialsProvider.create(AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)))
			.build();
	private static DynamoDbEnhancedClient EnhancedDBClient = DynamoDbEnhancedClient.builder().dynamoDbClient(ddbclient)
			.build();
	private static final DynamoDbTable<LoanAccountData> LoanAccountTable = EnhancedDBClient
			.table(CollectionsConstants.LOAN_ACCOUNT_TABLE_NAME, TableSchema.fromBean(LoanAccountData.class));
	

	public static LoanAccountData queryByItemID(String partionKey, String sortKey) {

		LoanAccountData account = new LoanAccountData();

		try {
			account = LoanAccountTable.getItem(Key.builder().partitionValue(partionKey).sortValue(sortKey).build());
		} catch (DynamoDbException ddbe) {
			System.err.println(ddbe.getMessage());
		}
		return account;
	}
	
	private void updateLoanAccount(LoanAccountData account) {
		LoanAccountTable.updateItem(account);
	}

	public void updateLoanAccountData(LoanAccountData account) {
		updateLoanAccount(account);
	}
}

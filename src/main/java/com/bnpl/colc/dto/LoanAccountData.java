package com.bnpl.colc.dto;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;


//@DynamoDBTable(tableName="OP_LoanAccount")
@DynamoDbBean
public class LoanAccountData {
    private String refID;
    private String refDate;
	private String accountNumber;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private boolean badDebt;
	private String badDebtDeclarationDate;
	
	private PaymentData paymentData;
	
	private List<Instalment> instalments;

	@DynamoDBIgnore
	@DynamoDbAttribute("AccountNumber")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@DynamoDbAttribute("FirstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@DynamoDbAttribute("LastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@DynamoDbAttribute("PaymentData")
	public PaymentData getPaymentData() {
		return paymentData;
	}

	public void setPaymentData(PaymentData paymentData) {
		this.paymentData = paymentData;
	}

	@DynamoDBIgnore
	@DynamoDbAttribute("PhoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@DynamoDbAttribute("BadDebt")
	public boolean isBadDebt() {
		return badDebt;
	}

	public void setBadDebt(boolean badDebt) {
		this.badDebt = badDebt;
	}

	@DynamoDbAttribute("Installments")
	public List<Instalment> getInstalments() {
		return instalments;
	}

	public void setInstalments(List<Instalment> installments) {
		this.instalments = installments;
	}

	@DynamoDbAttribute("BadDebtDeclarationDate")
	public String getBadDebtDeclarationDate() {
		return badDebtDeclarationDate;
	}

	public void setBadDebtDeclarationDate(String badDebtDeclarationDate) {
		this.badDebtDeclarationDate = badDebtDeclarationDate;
	}

	@DynamoDbPartitionKey
	@DynamoDbAttribute("RefID")
	public String getRefID() {
		return refID;
	}

	public void setRefID(String refID) {
		this.refID = refID;
	}

	@DynamoDbSortKey
	@DynamoDbAttribute("RefDate")
	public String getRefDate() {
		return refDate;
	}

	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}
}

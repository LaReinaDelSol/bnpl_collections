package com.bnpl.colc.dto;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class PaymentStatus {
	private double amount;
	private String currencySymbol;
	private String paymentTransactionDate;
	private boolean transactionStatus;
	private String transactionDesc; // auto debit instalment 2/3
	private String transactionComment; // empty for POC

	@DynamoDbAttribute("Amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@DynamoDbAttribute("TransactionStatus")
	public boolean isTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(boolean TransactionStatus) {
		this.transactionStatus = TransactionStatus;
	}

	@DynamoDbAttribute("CurrencySymbol")
	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	@DynamoDbAttribute("TransactionDesc")
	public String getTransactionDesc() {
		return transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}

	@DynamoDbAttribute("TransactionComment")
	public String getTransactionComment() {
		return transactionComment;
	}

	public void setTransactionComment(String transactionComment) {
		this.transactionComment = transactionComment;
	}

	@DynamoDbAttribute("PaymentTransactionDate")
	public String getPaymentTransactionDate() {
		return paymentTransactionDate;
	}

	public void setPaymentTransactionDate(String paymentTransactionDate) {
		this.paymentTransactionDate = paymentTransactionDate;
	}
}
package com.bnpl.colc.dto;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class Instalment {

	private double amount;
	private double feeAmount;
	private String currencySymbol;
	private String dueDate;
	private int failedAttempts;
	private String installmentStatus;
	private String transactionRef;

	public enum InstalmentStatus {
		PAID, PENDING, OVERDUE
	}

	@DynamoDbAttribute("Amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@DynamoDbAttribute("CurrencySymbol")
	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	@DynamoDbAttribute("FailedAttempts")
	public int getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	@DynamoDbAttribute("TransactionRef")
	public String getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}

	@DynamoDbAttribute("FeeAmount")
	public double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}

	@DynamoDbAttribute("InstallmentStatus")
	public String getInstallmentStatus() {
		return installmentStatus;
	}

	public void setInstallmentStatus(String status) {
		this.installmentStatus = InstalmentStatus.valueOf(status).name();
	}

	@DynamoDbAttribute("DueDate")
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}
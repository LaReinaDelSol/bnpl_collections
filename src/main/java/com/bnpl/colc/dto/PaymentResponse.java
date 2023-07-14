package com.bnpl.colc.dto;

public class PaymentResponse {

	private String transactionRefID;
	private double amount;
	private boolean status;
	private String transactionDate;

	public String getTransactionRefID() {
		return transactionRefID;
	}

	public void setTransactionRefID(String transactionRefID) {
		this.transactionRefID = transactionRefID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

}

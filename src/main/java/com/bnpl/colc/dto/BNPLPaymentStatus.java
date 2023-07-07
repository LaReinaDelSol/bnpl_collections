package com.bnpl.colc.dto;

import java.util.Date;

public class BNPLPaymentStatus {
	private String id;
	
	private String amount;
	private Date paymentInitiationDate;
	private Date paymentAuthorizationDate;
	private boolean transactionSuccessful;
	
	private String errorMessage;
	private boolean error;
	
	private String successMessage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getPaymentInitiationDate() {
		return paymentInitiationDate;
	}

	public void setPaymentInitiationDate(Date paymentInitiationDate) {
		this.paymentInitiationDate = paymentInitiationDate;
	}

	public Date getPaymentAuthorizationDate() {
		return paymentAuthorizationDate;
	}

	public void setPaymentAuthorizationDate(Date paymentAuthorizationDate) {
		this.paymentAuthorizationDate = paymentAuthorizationDate;
	}

	public boolean isTransactionSuccessful() {
		return transactionSuccessful;
	}

	public void setTransactionSuccessful(boolean transactionSuccessful) {
		this.transactionSuccessful = transactionSuccessful;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	
	
	
}

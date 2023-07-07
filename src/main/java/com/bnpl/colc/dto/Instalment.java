package com.bnpl.colc.dto;

import java.util.Date;

public class Instalment {
	
	private int amount;
	private int feeAmount;
	private String currencySymbol;
	private Date dueDate;
	private int failedAttempts;
	private boolean installmentStatus;
	private Date paidOn;
	private String transactionRef;		
	
	public Instalment(int amount, String currencySymbol, Date dueDate, int failedAttempts,
			boolean installmentStatus, Date paidOn, String transactionRef) {
		super();
		this.amount = amount;
		this.currencySymbol = currencySymbol;
		this.dueDate = dueDate;
		this.failedAttempts = failedAttempts;
		this.installmentStatus = installmentStatus;
		this.paidOn = paidOn;
		this.transactionRef = transactionRef;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getFailedAttempts() {
		return failedAttempts;
	}
	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}
	public boolean isInstallmentStatus() {
		return installmentStatus;
	}
	public void setInstallmentStatus(boolean installmentStatus) {
		this.installmentStatus = installmentStatus;
	}
	public Date getPaidOn() {
		return paidOn;
	}
	public void setPaidOn(Date paidOn) {
		this.paidOn = paidOn;
	}
	public String getTransactionRef() {
		return transactionRef;
	}
	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}

	public int getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(int feeAmount) {
		this.feeAmount = feeAmount;
	}

}

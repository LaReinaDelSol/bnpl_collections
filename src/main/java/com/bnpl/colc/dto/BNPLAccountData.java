package com.bnpl.colc.dto;

import java.util.Date;

public class BNPLAccountData {
	private String id;
	private String accountNumber;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private boolean badDebt;
	private Date badDebtDeclarationDate;
	
	private BNPLPaymentData bnplPaymentData;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BNPLPaymentData getBnplPaymentData() {
		if(bnplPaymentData == null) {
			bnplPaymentData = new BNPLPaymentData();
		}
		return bnplPaymentData;
	}

	public void setBnplPaymentData(BNPLPaymentData bnplPaymentData) {
		this.bnplPaymentData = bnplPaymentData;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isBadDebt() {
		return badDebt;
	}

	public void setBadDebt(boolean badDebt) {
		this.badDebt = badDebt;
	}

	public Date getBadDebtDeclarationDate() {
		return badDebtDeclarationDate;
	}

	public void setBadDebtDeclarationDate(Date badDebtDeclarationDate) {
		this.badDebtDeclarationDate = badDebtDeclarationDate;
	}

}

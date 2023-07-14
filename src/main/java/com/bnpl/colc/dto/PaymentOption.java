package com.bnpl.colc.dto;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class PaymentOption {

	private String AddedOn;
	private String CardNumber;
	private String CardType;
	private String IsActive;
	private String OptionStatus;
	private String PaymentType;
	private String Provider;
	private String Token;

	public String getAddedOn() {
		return AddedOn;
	}

	public void setAddedOn(String addedOn) {
		AddedOn = addedOn;
	}

	public String getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}

	public String getCardType() {
		return CardType;
	}

	public void setCardType(String cardType) {
		CardType = cardType;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String isActive) {
		IsActive = isActive;
	}

	public String getOptionStatus() {
		return OptionStatus;
	}

	public void setOptionStatus(String optionStatus) {
		OptionStatus = optionStatus;
	}

	public String getPaymentType() {
		return PaymentType;
	}

	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}

	public String getProvider() {
		return Provider;
	}

	public void setProvider(String provider) {
		Provider = provider;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

}

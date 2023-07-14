package com.bnpl.colc.dto;

import java.util.List;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class PaymentData {
	private boolean defaultFeeApplied;
	private List<PaymentStatus> paymentStatusList = null;
	private boolean ceaseAutomatedCollections;
	private String automatedCollectionsCeaseDate;
	
	@DynamoDbAttribute("DefaultFeeApplied")
	public boolean isDefaultFeeApplied() {
		return defaultFeeApplied;
	}

	public void setDefaultFeeApplied(boolean defaultFeeApplied) {
		this.defaultFeeApplied = defaultFeeApplied;
	}

	@DynamoDbAttribute("PaymentStatusList")
	public List<PaymentStatus> getPaymentStatusList() {
		return this.paymentStatusList;
	}

	public void setPaymentStatusList(List<PaymentStatus> paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
	}

	@DynamoDbAttribute("CeaseAutomatedCollections")
	public boolean isCeaseAutomatedCollections() {
		return ceaseAutomatedCollections;
	}

	public void setCeaseAutomatedCollections(boolean ceaseAutomatedCollections) {
		this.ceaseAutomatedCollections = ceaseAutomatedCollections;
	}

	@DynamoDbAttribute("AutomatedCollectionsCeaseDate")
	public String getAutomatedCollectionsCeaseDate() {
		return automatedCollectionsCeaseDate;
	}

	public void setAutomatedCollectionsCeaseDate(String automatedCollectionsCeaseDate) {
		this.automatedCollectionsCeaseDate = automatedCollectionsCeaseDate;
	}
}
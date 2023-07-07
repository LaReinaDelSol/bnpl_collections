package com.bnpl.colc.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BNPLPaymentData {
	private String id;

	private Instalment[] instalments = new Instalment[3];
	private int defaultFeeAmount;
	private boolean defaultFeeApplied;	
	private List<BNPLPaymentStatus> paymentStatusList = null;
	private boolean ceaseAutomatedCollections;
	private Date automatedCollectionsCeaseDate;
	
	public int getDefaultFeeAmount() {
		return defaultFeeAmount;
	}
	public void setDefaultFeeAmount(int defaultFeeAmount) {
		this.defaultFeeAmount = defaultFeeAmount;
	}
	public boolean isDefaultFeeApplied() {
		return defaultFeeApplied;
	}
	public void setDefaultFeeApplied(boolean defaultFeeApplied) {
		this.defaultFeeApplied = defaultFeeApplied;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<BNPLPaymentStatus> getPaymentStatusList() {
		return this.paymentStatusList;
	}
	public void setPaymentStatusList(List<BNPLPaymentStatus> paymentStatusList) {
		this.paymentStatusList = new ArrayList<BNPLPaymentStatus>();
	}
	public Instalment[] getInstalments() {
		return instalments;
	}
	public void setInstalments(Instalment[] instalments) {
		this.instalments = instalments;
	}
	
	public boolean isCeaseAutomatedCollections() {
		return ceaseAutomatedCollections;
	}
	public void setCeaseAutomatedCollections(boolean ceaseAutomatedCollections) {
		this.ceaseAutomatedCollections = ceaseAutomatedCollections;
	}
	public Date getAutomatedCollectionsCeaseDate() {
		return automatedCollectionsCeaseDate;
	}
	public void setAutomatedCollectionsCeaseDate(Date automatedCollectionsCeaseDate) {
		this.automatedCollectionsCeaseDate = automatedCollectionsCeaseDate;
	}
	
}

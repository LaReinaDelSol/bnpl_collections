package com.bnpl.colc.dto;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class ContactInfo {

	private String ContactType;
	private Boolean IsPrimary;
	private Boolean IsVerified;
	private String MobileNumber;
	private String SmsConsentOn;

	public String getContactType() {
		return ContactType;
	}

	public void setContactType(String ContactType) {
		this.ContactType = ContactType;
	}

	public Boolean getIsPrimary() {
		return IsPrimary;
	}

	public void setIsPrimary(Boolean IsPrimary) {
		this.IsPrimary = IsPrimary;
	}

	public Boolean getIsVerified() {
		return IsVerified;
	}

	public void setIsVerified(Boolean IsVerified) {
		this.IsVerified = IsVerified;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String MobileNumber) {
		this.MobileNumber = MobileNumber;
	}

	public String getSmsConsentOn() {
		return SmsConsentOn;
	}

	public void setSmsConsentOn(String SmsConsentOn) {
		this.SmsConsentOn = SmsConsentOn;
	}

}

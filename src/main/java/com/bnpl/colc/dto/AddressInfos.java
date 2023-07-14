package com.bnpl.colc.dto;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class AddressInfos {

	private String AddressLine1;
	private String AddressLine2;
	private String AddressLine3;
	private String AddressType;
	private String City;
	private Boolean IsPrimary;
	private String Province;
	private String ZipCode;

	public String getAddressLine1() {
		return AddressLine1;
	}

	public void setAddressLine1(String AddressLine1) {
		this.AddressLine1 = AddressLine1;
	}

	public String getAddressLine2() {
		return AddressLine2;
	}

	public void setAddressLine2(String AddressLine2) {
		this.AddressLine2 = AddressLine2;
	}

	public String getAddressLine3() {
		return AddressLine3;
	}

	public void setAddressLine3(String AddressLine3) {
		this.AddressLine3 = AddressLine3;
	}

	public String getAddressType() {
		return AddressType;
	}

	public void setAddressType(String AddressType) {
		this.AddressType = AddressType;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String City) {
		this.City = City;
	}

	public Boolean getIsPrimary() {
		return IsPrimary;
	}

	public void setIsPrimary(Boolean IsPrimary) {
		this.IsPrimary = IsPrimary;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String Province) {
		this.Province = Province;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String ZipCode) {
		this.ZipCode = ZipCode;
	}

}

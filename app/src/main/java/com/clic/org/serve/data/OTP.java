package com.clic.org.serve.data;

public class OTP {

	private String customerID;
	private String otpNum;
	private String status;
	private int responseCode;

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getOtpNum() {
		return otpNum;
	}

	public void setOtpNum(String otpNum) {
		this.otpNum = otpNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

}

package com.clic.org.serve.data;

import java.util.ArrayList;

public class ServiceTypeResponse {

	private ArrayList<ServiceType> serviceRequest;
	private String serviceType;
	private String status;
	private String responseCode;
	private String customerID;

	public ArrayList<ServiceType> getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ArrayList<ServiceType> serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	

}

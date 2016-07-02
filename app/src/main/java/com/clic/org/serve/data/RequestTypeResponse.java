package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RequestTypeResponse implements Parcelable {

	private ArrayList<RequestType> requestType;
	private String serviceType;
	private String status;
	private String responseCode;
	private String customerID;

	

	public ArrayList<RequestType> getRequestType() {
		return requestType;
	}

	public void setRequestType(ArrayList<RequestType> requestType) {
		this.requestType = requestType;
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


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(this.requestType);
		dest.writeString(this.serviceType);
		dest.writeString(this.status);
		dest.writeString(this.responseCode);
		dest.writeString(this.customerID);
	}

	public RequestTypeResponse() {
	}

	protected RequestTypeResponse(Parcel in) {
		this.requestType = new ArrayList<RequestType>();
		in.readList(this.requestType, RequestType.class.getClassLoader());
		this.serviceType = in.readString();
		this.status = in.readString();
		this.responseCode = in.readString();
		this.customerID = in.readString();
	}

	public static final Parcelable.Creator<RequestTypeResponse> CREATOR = new Parcelable.Creator<RequestTypeResponse>() {
		@Override
		public RequestTypeResponse createFromParcel(Parcel source) {
			return new RequestTypeResponse(source);
		}

		@Override
		public RequestTypeResponse[] newArray(int size) {
			return new RequestTypeResponse[size];
		}
	};
}

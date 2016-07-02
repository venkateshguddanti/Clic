package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceRequest implements Parcelable {

	private String typeOfRequest;
	private String description;
	private String lat;
	private String lang;
	private String imageString;
	private String scheduledDate;
	private String customerID;

	private String customerItemID;
	private String repaiTypeId;
	private String status;

	public String getCustomerItemID() {
		return customerItemID;
	}

	public void setCustomerItemID(String customerItemID) {
		this.customerItemID = customerItemID;
	}

	public String getRepaiTypeId() {
		return repaiTypeId;
	}

	public void setRepaiTypeId(String repaiTypeId) {
		this.repaiTypeId = repaiTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getImageString() {
		return imageString;
	}

	public void setImageString(String imageString) {
		this.imageString = imageString;
	}

	public String getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getTypeOfRequest() {
		return typeOfRequest;
	}

	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.typeOfRequest);
		dest.writeString(this.description);
		dest.writeString(this.lat);
		dest.writeString(this.lang);
		dest.writeString(this.imageString);
		dest.writeString(this.scheduledDate);
		dest.writeString(this.customerID);
		dest.writeString(this.customerItemID);
		dest.writeString(this.repaiTypeId);
		dest.writeString(this.status);
	}

	public ServiceRequest() {
	}

	protected ServiceRequest(Parcel in) {
		this.typeOfRequest = in.readString();
		this.description = in.readString();
		this.lat = in.readString();
		this.lang = in.readString();
		this.imageString = in.readString();
		this.scheduledDate = in.readString();
		this.customerID = in.readString();
		this.customerItemID = in.readString();
		this.repaiTypeId = in.readString();
		this.status = in.readString();
	}

	public static final Parcelable.Creator<ServiceRequest> CREATOR = new Parcelable.Creator<ServiceRequest>() {
		@Override
		public ServiceRequest createFromParcel(Parcel source) {
			return new ServiceRequest(source);
		}

		@Override
		public ServiceRequest[] newArray(int size) {
			return new ServiceRequest[size];
		}
	};
}

package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceType implements Parcelable {

	private String serviceTypeID;
	private String serviceType;
	private String status;

	public String getServiceTypeID() {
		return serviceTypeID;
	}

	public void setServiceTypeID(String serviceTypeID) {
		this.serviceTypeID = serviceTypeID;
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.serviceTypeID);
		dest.writeString(this.serviceType);
		dest.writeString(this.status);
	}

	public ServiceType() {
	}

	protected ServiceType(Parcel in) {
		this.serviceTypeID = in.readString();
		this.serviceType = in.readString();
		this.status = in.readString();
	}

	public static final Parcelable.Creator<ServiceType> CREATOR = new Parcelable.Creator<ServiceType>() {
		@Override
		public ServiceType createFromParcel(Parcel source) {
			return new ServiceType(source);
		}

		@Override
		public ServiceType[] newArray(int size) {
			return new ServiceType[size];
		}
	};
}

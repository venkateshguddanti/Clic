package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestType implements Parcelable {

	private String requestTypeID;
	private String requestType;
	private String description;
	public String getRequestTypeID() {
		return requestTypeID;
	}
	public void setRequestTypeID(String requestTypeID) {
		this.requestTypeID = requestTypeID;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.requestTypeID);
		dest.writeString(this.requestType);
		dest.writeString(this.description);
	}

	public RequestType() {
	}

	protected RequestType(Parcel in) {
		this.requestTypeID = in.readString();
		this.requestType = in.readString();
		this.description = in.readString();
	}

	public static final Parcelable.Creator<RequestType> CREATOR = new Parcelable.Creator<RequestType>() {
		@Override
		public RequestType createFromParcel(Parcel source) {
			return new RequestType(source);
		}

		@Override
		public RequestType[] newArray(int size) {
			return new RequestType[size];
		}
	};
}

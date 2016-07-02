package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Brand implements Parcelable {

	private String brandID;
	private String brandName;
	private String description;
	private String logoURL;
	private String status;

	public String getBrandID() {
		return brandID;
	}

	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
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
		dest.writeString(this.brandID);
		dest.writeString(this.brandName);
		dest.writeString(this.description);
		dest.writeString(this.logoURL);
		dest.writeString(this.status);
	}

	public Brand() {
	}

	protected Brand(Parcel in) {
		this.brandID = in.readString();
		this.brandName = in.readString();
		this.description = in.readString();
		this.logoURL = in.readString();
		this.status = in.readString();
	}

	public static final Parcelable.Creator<Brand> CREATOR = new Parcelable.Creator<Brand>() {
		@Override
		public Brand createFromParcel(Parcel source) {
			return new Brand(source);
		}

		@Override
		public Brand[] newArray(int size) {
			return new Brand[size];
		}
	};
}

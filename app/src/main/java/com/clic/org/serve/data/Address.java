package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Address implements Parcelable {

	private String addressID;
	private String houseNumber;
	private String streetName;
	private String city;
	private String state;
	private String country;
	private int pinCode;
	private String status;

	public String getAddressID() {
		return addressID;
	}

	public void setAddressID(String addressID) {
		this.addressID = addressID;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
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
		dest.writeString(this.addressID);
		dest.writeString(this.houseNumber);
		dest.writeString(this.streetName);
		dest.writeString(this.city);
		dest.writeString(this.state);
		dest.writeString(this.country);
		dest.writeInt(this.pinCode);
		dest.writeString(this.status);
	}

	public Address() {
	}

	protected Address(Parcel in) {
		this.addressID = in.readString();
		this.houseNumber = in.readString();
		this.streetName = in.readString();
		this.city = in.readString();
		this.state = in.readString();
		this.country = in.readString();
		this.pinCode = in.readInt();
		this.status = in.readString();
	}

	public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
		@Override
		public Address createFromParcel(Parcel source) {
			return new Address(source);
		}

		@Override
		public Address[] newArray(int size) {
			return new Address[size];
		}
	};
}

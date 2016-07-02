package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

	private String brandID;
	private String productID;
	private String productName;
	private String description;
	private String status;

	public String getBrandID() {
		return brandID;
	}

	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		dest.writeString(this.productID);
		dest.writeString(this.productName);
		dest.writeString(this.description);
		dest.writeString(this.status);
	}

	public Product() {
	}

	protected Product(Parcel in) {
		this.brandID = in.readString();
		this.productID = in.readString();
		this.productName = in.readString();
		this.description = in.readString();
		this.status = in.readString();
	}

	public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
		@Override
		public Product createFromParcel(Parcel source) {
			return new Product(source);
		}

		@Override
		public Product[] newArray(int size) {
			return new Product[size];
		}
	};
}

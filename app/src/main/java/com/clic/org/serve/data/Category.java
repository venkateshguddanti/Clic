package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

	private String brandID;
	private String productID;
	private String categoryID;
	private String description;
	private String categoryName;
	private String status;

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBrandID() {
		return brandID;
	}

	public void setBrandID(String brandID) {
		this.brandID = brandID;
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
		dest.writeString(this.categoryID);
		dest.writeString(this.description);
		dest.writeString(this.categoryName);
		dest.writeString(this.status);
	}

	public Category() {
	}

	protected Category(Parcel in) {
		this.brandID = in.readString();
		this.productID = in.readString();
		this.categoryID = in.readString();
		this.description = in.readString();
		this.categoryName = in.readString();
		this.status = in.readString();
	}

	public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
		@Override
		public Category createFromParcel(Parcel source) {
			return new Category(source);
		}

		@Override
		public Category[] newArray(int size) {
			return new Category[size];
		}
	};
}

package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

public class SubCategory implements Parcelable {

	private String brandID;
	private String productID;
	private String description;
	private String subCategoryName;
	private String categoryID;
	private String subcategoryID;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(String subcategoryID) {
		this.subcategoryID = subcategoryID;
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
		dest.writeString(this.description);
		dest.writeString(this.subCategoryName);
		dest.writeString(this.categoryID);
		dest.writeString(this.subcategoryID);
		dest.writeString(this.status);
	}

	public SubCategory() {
	}

	protected SubCategory(Parcel in) {
		this.brandID = in.readString();
		this.productID = in.readString();
		this.description = in.readString();
		this.subCategoryName = in.readString();
		this.categoryID = in.readString();
		this.subcategoryID = in.readString();
		this.status = in.readString();
	}

	public static final Parcelable.Creator<SubCategory> CREATOR = new Parcelable.Creator<SubCategory>() {
		@Override
		public SubCategory createFromParcel(Parcel source) {
			return new SubCategory(source);
		}

		@Override
		public SubCategory[] newArray(int size) {
			return new SubCategory[size];
		}
	};
}

package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

	private String brandID;
	private String productID;
	private String description;
	private String modelNumber;
	private String categoryID;
	private String subcategoryID;
	private String yearop;
	private String itemID;
	private String specification;
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

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
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

	public String getYearop() {
		return yearop;
	}

	public void setYearop(String yearop) {
		this.yearop = yearop;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
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
		dest.writeString(this.modelNumber);
		dest.writeString(this.categoryID);
		dest.writeString(this.subcategoryID);
		dest.writeString(this.yearop);
		dest.writeString(this.itemID);
		dest.writeString(this.specification);
		dest.writeString(this.status);
	}

	public Item() {
	}

	protected Item(Parcel in) {
		this.brandID = in.readString();
		this.productID = in.readString();
		this.description = in.readString();
		this.modelNumber = in.readString();
		this.categoryID = in.readString();
		this.subcategoryID = in.readString();
		this.yearop = in.readString();
		this.itemID = in.readString();
		this.specification = in.readString();
		this.status = in.readString();
	}

	public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
		@Override
		public Item createFromParcel(Parcel source) {
			return new Item(source);
		}

		@Override
		public Item[] newArray(int size) {
			return new Item[size];
		}
	};
}

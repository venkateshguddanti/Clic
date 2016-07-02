package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UserItem implements Parcelable {

	private String brandID;
	private String productID;
	private String description;
	private String modelNumber;
	private String categoryID;
	private String subcategoryID;
	private String yearop;
	private String itemID;
	private String customerID;
	private String invoiceCopy;
	private String userMannual;
	private String warrentyMonths;
	private String specification;
	private String status;
	
	private String sameAddress;
	
	public String getSameAddress() {
		return sameAddress;
	}

	public void setSameAddress(String sameAddress) {
		this.sameAddress = sameAddress;
	}

	private Address address;
	
	


	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	private ArrayList<ItemDocs> itemDocs;

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

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getInvoiceCopy() {
		return invoiceCopy;
	}

	public void setInvoiceCopy(String invoiceCopy) {
		this.invoiceCopy = invoiceCopy;
	}

	public String getUserMannual() {
		return userMannual;
	}

	public void setUserMannual(String userMannual) {
		this.userMannual = userMannual;
	}

	
	public String getWarrentyMonths() {
		return warrentyMonths;
	}

	public void setWarrentyMonths(String warrentyMonths) {
		this.warrentyMonths = warrentyMonths;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public ArrayList<ItemDocs> getItemDocs() {
		return itemDocs;
	}

	public void setItemDocs(ArrayList<ItemDocs> itemDocs) {
		this.itemDocs = itemDocs;
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
		dest.writeString(this.customerID);
		dest.writeString(this.invoiceCopy);
		dest.writeString(this.userMannual);
		dest.writeString(this.warrentyMonths);
		dest.writeString(this.specification);
		dest.writeString(this.status);
		dest.writeString(this.sameAddress);
		dest.writeParcelable(this.address, flags);
		dest.writeList(this.itemDocs);
	}

	public UserItem() {
	}

	protected UserItem(Parcel in) {
		this.brandID = in.readString();
		this.productID = in.readString();
		this.description = in.readString();
		this.modelNumber = in.readString();
		this.categoryID = in.readString();
		this.subcategoryID = in.readString();
		this.yearop = in.readString();
		this.itemID = in.readString();
		this.customerID = in.readString();
		this.invoiceCopy = in.readString();
		this.userMannual = in.readString();
		this.warrentyMonths = in.readString();
		this.specification = in.readString();
		this.status = in.readString();
		this.sameAddress = in.readString();
		this.address = in.readParcelable(Address.class.getClassLoader());
		this.itemDocs = new ArrayList<ItemDocs>();
		in.readList(this.itemDocs, ItemDocs.class.getClassLoader());
	}

	public static final Parcelable.Creator<UserItem> CREATOR = new Parcelable.Creator<UserItem>() {
		@Override
		public UserItem createFromParcel(Parcel source) {
			return new UserItem(source);
		}

		@Override
		public UserItem[] newArray(int size) {
			return new UserItem[size];
		}
	};
}

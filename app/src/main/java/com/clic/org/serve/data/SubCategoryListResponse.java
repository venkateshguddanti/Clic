package com.clic.org.serve.data;

import java.util.ArrayList;

public class SubCategoryListResponse {

	private String responseCode;
	private String status;
	private ArrayList<SubCategory> subcategorylist;
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<SubCategory> getSubcategorylist() {
		return subcategorylist;
	}
	public void setSubcategorylist(ArrayList<SubCategory> subcategorylist) {
		this.subcategorylist = subcategorylist;
	}
	

	
}
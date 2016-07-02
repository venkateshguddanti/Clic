package com.clic.org.serve.data;

import java.util.ArrayList;

public class ItemsListResponse {

	private String responseCode;
	private String status;
	private ArrayList<Item> itemsList;
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
	public ArrayList<Item> getItemsList() {
		return itemsList;
	}
	public void setItemsList(ArrayList<Item> itemsList) {
		this.itemsList = itemsList;
	}	
}
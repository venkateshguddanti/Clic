package com.clic.org.serve.data;

public class Ticket {

	private String ticketID;
	private String customerID;
	private String customerItemID;
	private String raisedBy;
	private String query;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerID() {
		return customerID;
	}

	public String getTicketID() {
		return ticketID;
	}

	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerItemID() {
		return customerItemID;
	}

	public void setCustomerItemID(String customerItemID) {
		this.customerItemID = customerItemID;
	}

	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}

package com.example.payment;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceCreated {

	public InvoiceCreated () {
	}

	public InvoiceCreated (
		String id, 
		String customerid, 
		java.math.BigDecimal invoiceamount) {
		this.id = id;
		this.customerid = customerid;
		this.invoiceamount = invoiceamount;
	}


	private String id;
	private String customerid;
	private java.math.BigDecimal invoiceamount;

	public String getId() {
		return id;
	}

	public InvoiceCreated setId(String id) {
		this.id = id;
		return this;
	}


	public String getCustomerid() {
		return customerid;
	}

	public InvoiceCreated setCustomerid(String customerid) {
		this.customerid = customerid;
		return this;
	}


	public java.math.BigDecimal getInvoiceamount() {
		return invoiceamount;
	}

	public InvoiceCreated setInvoiceamount(java.math.BigDecimal invoiceamount) {
		this.invoiceamount = invoiceamount;
		return this;
	}


	public String toString() {
		return "InvoiceCreated ["
		+ " id: " + id
		+ " customerid: " + customerid
		+ " invoiceamount: " + invoiceamount
		+ " ]";
	}
}


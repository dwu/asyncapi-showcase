package com.example.shop;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderCreated {

	public OrderCreated () {
	}

	public OrderCreated (
		String id, 
		String customerid, 
		String productid, 
		Integer amount) {
		this.id = id;
		this.customerid = customerid;
		this.productid = productid;
		this.amount = amount;
	}


	private String id;
	private String customerid;
	private String productid;
	private Integer amount;

	public String getId() {
		return id;
	}

	public OrderCreated setId(String id) {
		this.id = id;
		return this;
	}


	public String getCustomerid() {
		return customerid;
	}

	public OrderCreated setCustomerid(String customerid) {
		this.customerid = customerid;
		return this;
	}


	public String getProductid() {
		return productid;
	}

	public OrderCreated setProductid(String productid) {
		this.productid = productid;
		return this;
	}


	public Integer getAmount() {
		return amount;
	}

	public OrderCreated setAmount(Integer amount) {
		this.amount = amount;
		return this;
	}


	public String toString() {
		return "OrderCreated ["
		+ " id: " + id
		+ " customerid: " + customerid
		+ " productid: " + productid
		+ " amount: " + amount
		+ " ]";
	}
}


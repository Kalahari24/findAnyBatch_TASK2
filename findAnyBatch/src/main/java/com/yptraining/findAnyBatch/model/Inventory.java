package com.yptraining.findAnyBatch.model;

public class Inventory {
	
	private Integer id;
	private String itemType;
	private String item;
	private Integer quantity;
	private String status;
	private Integer itemPrice;
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Inventory(Integer id, String itemType, String item, Integer quantity, String status, Integer itemPrice,
			String description) {
		super();
		this.id = id;
		this.itemType = itemType;
		this.item = item;
		this.quantity = quantity;
		this.status = status;
		this.itemPrice = itemPrice;
		this.description = description;
	}
	public Inventory() {
	}
	
	
	
	
	

}

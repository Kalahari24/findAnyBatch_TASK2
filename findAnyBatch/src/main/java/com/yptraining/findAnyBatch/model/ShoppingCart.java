package com.yptraining.findAnyBatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
	
	@Id
    @Column(name="id")
	private Integer id;
	
    @Column(name="item")
	private String item;
	
    @Column(name="itemtype")
	private String itemType;
	
    @Column(name="itemprice")
	private Integer itemPrice;
	
    @Column(name="description")
	private String description;
	
    @Column(name = "quantity")
    private Integer quantity;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public ShoppingCart(Integer id, String item, String itemType, Integer itemPrice, String description,
			Integer quantity) {
		super();
		this.id = id;
		this.item = item;
		this.itemType = itemType;
		this.itemPrice = itemPrice;
		this.description = description;
		this.quantity = quantity;
	}
	public ShoppingCart() {
	}
	
	

}

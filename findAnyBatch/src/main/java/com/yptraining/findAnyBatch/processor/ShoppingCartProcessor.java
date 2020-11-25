package com.yptraining.findAnyBatch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.yptraining.findAnyBatch.model.Inventory;
import com.yptraining.findAnyBatch.model.ShoppingCart;
import com.yptraining.findAnyBatch.repositories.findAnyRepository;

public class ShoppingCartProcessor implements ItemProcessor<Inventory, ShoppingCart>{
	
   private static final Logger log = LoggerFactory.getLogger(ShoppingCartProcessor.class);
	
   @Autowired
   findAnyRepository repository;
   
	@Override
	public ShoppingCart process(final Inventory inventory) throws Exception {

		
//		final String itemname = inventory.getItem().toUpperCase().trim();
//		final String itemtype = inventory.getItemType().toUpperCase().trim();
//		final String description = inventory.getDescription().toUpperCase().trim();
//		System.out.println(repository.findByItemType(inventory.getItem()));
//		final String name=repository.findByItemType(inventory.getItem());
//		System.out.println("Name : "+name);
//		System.out.println("itemtype: "+itemname);
//		if(name == null) {
//			final ShoppingCart	cartdata = new ShoppingCart(inventory.getId(),itemtype,itemname,inventory.getItemPrice(),description,inventory.getQuantity());
//			return cartdata;
//		}
//		else {
//			Integer quantity = repository.findQuantityByItemType(name);
//			quantity= quantity+inventory.getQuantity();
//			System.out.println("Updated quantity"+quantity);
//			final ShoppingCart cartdata = new ShoppingCart(inventory.getId(),itemtype,itemname,inventory.getItemPrice(),description,quantity);
//			return cartdata;
//	}
		
		
		final String itemname = inventory.getItem().toUpperCase().trim();
		final String itemtype = inventory.getItemType().toUpperCase().trim();
		final String description = inventory.getDescription().toUpperCase().trim();
		final String name = repository.findByItemType(inventory.getItem());
		if (name == null) {
			final ShoppingCart cartdata = new ShoppingCart(inventory.getId(), itemtype, itemname,
					inventory.getItemPrice(), description, inventory.getQuantity());
			return cartdata;
		} else {
			Boolean flag = false;
			Integer quantity = repository.findQuantityByItemType(name);
			Integer price = repository.findItemPriceByItemType(name);
			if (quantity != inventory.getQuantity()) {
				quantity = quantity + inventory.getQuantity();
				flag = true;
			}
			if (price != inventory.getItemPrice()) {
				price = inventory.getItemPrice();
				flag = true;
			}

			if (flag == true) {
				final ShoppingCart cartdata = new ShoppingCart(inventory.getId(), itemtype, itemname,
						price, description, quantity);
				return cartdata;
			} else {
				return null;
			}
		}
	}

}

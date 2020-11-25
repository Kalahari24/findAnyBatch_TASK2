package com.yptraining.findAnyBatch.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.yptraining.findAnyBatch.model.ShoppingCart;
import com.yptraining.findAnyBatch.repositories.findAnyRepository;

public class DBShoppingwriter implements ItemWriter<ShoppingCart> {
	
	 @Autowired
	 private findAnyRepository findAnyRepository;

	@Override
	public void write(List<? extends ShoppingCart> cartitems) throws Exception {
		findAnyRepository.saveAll(cartitems);

	}

}

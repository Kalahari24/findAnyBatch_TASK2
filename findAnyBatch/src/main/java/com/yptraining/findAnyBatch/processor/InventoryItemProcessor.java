package com.yptraining.findAnyBatch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.yptraining.findAnyBatch.model.Inventory;

public class InventoryItemProcessor implements ItemProcessor<Inventory, Inventory>{
	
	@Override
	public Inventory process(Inventory inventory) throws Exception {
		return inventory;
	}

}

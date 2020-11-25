package com.yptraining.findAnyBatch.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yptraining.findAnyBatch.model.Inventory;

public class InventoryRowMapper implements RowMapper<Inventory> {

	@Override
	public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
		Inventory inventory = new Inventory();
		inventory.setId(rs.getInt("id"));
		inventory.setItemType(rs.getString("itemtype"));;
		inventory.setItem(rs.getString("item"));
		inventory.setQuantity(rs.getInt("quantity"));
		inventory.setStatus(rs.getString("status"));
		inventory.setItemPrice(rs.getInt("price"));
		inventory.setDescription(rs.getString("description"));
		return inventory;
	}

}

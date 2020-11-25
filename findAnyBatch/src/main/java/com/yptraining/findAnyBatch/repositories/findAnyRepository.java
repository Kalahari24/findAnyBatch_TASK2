package com.yptraining.findAnyBatch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yptraining.findAnyBatch.model.ShoppingCart;

public interface findAnyRepository extends JpaRepository<ShoppingCart, String>{
	
	@Query(value="select itemtype from shopping_cart  where itemType = ?1", nativeQuery=true)
	String findByItemType(String itemtype);
	
	@Query(value= "select quantity from shopping_cart  where itemtype = ?1" , nativeQuery=true)
	public Integer findQuantityByItemType(@Param("itemType") String itemtype);
	
	@Query(value= "select itemprice from shopping_cart  where itemtype = ?1" , nativeQuery=true)
    public Integer findItemPriceByItemType(@Param("itemType") String itemtype);

}

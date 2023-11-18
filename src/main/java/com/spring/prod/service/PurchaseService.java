package com.spring.prod.service;

import java.util.List;

import com.spring.prod.entity.Product;
import com.spring.prod.entity.Purchase;
import com.spring.prod.entity.User;

public interface PurchaseService {

	public boolean insertOrder(Purchase purchase);

	public Purchase getPurchaseById(int id);

	public List<Purchase> getAllPurchases();

	public void cancelOrder(int id);

	public List<Purchase> getAllCartProducts(List<Purchase> purchases);

	public User updateSellBalance(User user);

	public Product updateProduct(Product product);

	public User updateBuyerBal(User user);
	
	public List<Purchase> getPurchaseByBuyerId(int id);

	public List<Purchase> getPurchaseBySellerId(int id);
}

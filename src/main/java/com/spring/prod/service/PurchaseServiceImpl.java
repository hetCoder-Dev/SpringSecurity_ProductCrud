package com.spring.prod.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.prod.dao.PurchaseDao;
import com.spring.prod.entity.Product;
import com.spring.prod.entity.Purchase;
import com.spring.prod.entity.User;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService{
	
	@Autowired
	private PurchaseDao purchaseDao;
	

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}


	@Override
	@Transactional
	public boolean insertOrder(Purchase purchase) {		
		return purchaseDao.insertOrder(purchase);
		
	}

	@Override
	public List<Purchase> getAllPurchases() {		
		return  this.purchaseDao.getAllPurchases();
	}

	@Override
	@Transactional
	public void cancelOrder(int id) {
		purchaseDao.cancelOrder(id);
		
	}

	@Override
	public List<Purchase> getAllCartProducts(List<Purchase> purchases) {		
		return purchaseDao.getAllCartProducts(purchases);
	}


	@Override
	public Purchase getPurchaseById(int id) {		
		return purchaseDao.getPurchaseById(id);
	}


	@Override
	public User updateSellBalance(User user) {
		return purchaseDao.updateSellBalance(user);
	}


	@Override
	public Product updateProduct(Product product) {		
		return purchaseDao.updateProduct(product);
	}


	@Override
	public User updateBuyerBal(User user) {	
		return purchaseDao.updateBuyerBal(user);
	}


	@Override
	public List<Purchase> getPurchaseByBuyerId(int id) {		
		return purchaseDao.getPurchaseByBuyerId(id);
	}


	@Override
	public List<Purchase> getPurchaseBySellerId(int id) {		
		return purchaseDao.getPurchaseBySellerId(id);
	}



}

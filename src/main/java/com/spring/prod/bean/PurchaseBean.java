package com.spring.prod.bean;

import java.text.SimpleDateFormat;

import com.spring.prod.entity.Product;
import com.spring.prod.entity.Purchase;
import com.spring.prod.entity.User;

public class PurchaseBean {

	private int purchaseId;
	private String purchaseDt;
	private int totalUnit;
	private double totalCostPrice;
	private String user;
	private String user1;
	private String product;

	public PurchaseBean() {
		super();
	}

	public PurchaseBean(int purchaseId, String purchaseDt, int totalUnit, double totalCostPrice, String user,
			String user1, String product) {
		super();
		this.purchaseId = purchaseId;
		this.purchaseDt = purchaseDt;
		this.totalUnit = totalUnit;
		this.totalCostPrice = totalCostPrice;
		this.user = user;
		this.user1 = user1;
		this.product = product;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseDt() {
		return purchaseDt;
	}

	public void setPurchaseDt(String purchaseDt) {
		this.purchaseDt = purchaseDt;
	}

	public int getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(int totalUnit) {
		this.totalUnit = totalUnit;
	}

	public double getTotalCostPrice() {
		return totalCostPrice;
	}

	public void setTotalCostPrice(double totalCostPrice) {
		this.totalCostPrice = totalCostPrice;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public Purchase convertToPurchase() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Purchase pur = new Purchase();
		pur.setPurchaseId(purchaseId);
		Product prod = new Product();
		int pId = Integer.parseInt(product);
		prod.setId(pId);
		pur.setProduct(prod);
		pur.setTotalCostPrice(totalCostPrice);

		try {
			pur.setPurchaseDt(sdf.parse(purchaseDt));
		} catch (Exception e) {
			e.printStackTrace();
		}
		pur.setTotalUnit(totalUnit);

		User u = new User();
		int uId = Integer.parseInt(user);
		u.setId(uId);
		pur.setUser(u);
		return pur;

	}

}

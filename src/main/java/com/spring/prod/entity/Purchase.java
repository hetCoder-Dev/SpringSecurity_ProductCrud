package com.spring.prod.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int purchaseId;
	private Date purchaseDt;
	private int totalUnit;
	private double totalCostPrice;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "u_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "seller_id", referencedColumnName = "u_id")
	private User user1;

	@ManyToOne
	@JoinColumn(name = "pr_id")
	private Product product;

	public Purchase() {
		super();

	}

	public Purchase(int purchaseId, Date purchaseDt, int totalUnit, double totalCostPrice, User user, User user1,
			Product product) {
		super();
		this.purchaseId = purchaseId;
		this.purchaseDt = purchaseDt;
		this.totalUnit = totalUnit;
		this.totalCostPrice = totalCostPrice;
		this.user = user;
		this.user1 = user1;
		this.product = product;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Date getPurchaseDt() {
		return purchaseDt;
	}

	public void setPurchaseDt(Date purchaseDt) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", purchaseDt=" + purchaseDt + ", totalUnit=" + totalUnit
				+ ", totalCostPrice=" + totalCostPrice + ", user=" + user + ", user1=" + user1 + ", product=" + product
				+ "]";
	}

	

}

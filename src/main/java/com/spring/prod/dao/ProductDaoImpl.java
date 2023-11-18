package com.spring.prod.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.prod.bean.ProductBean;
import com.spring.prod.entity.Product;
import com.spring.prod.entity.User;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private HttpSession session;

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ProductBean> viewAllProducts() {
		List<ProductBean> list = new ArrayList<>();

		Session sess = sessionFactory.getCurrentSession();
		Query query = sess.createQuery("from Product");
		list = query.list();
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ProductBean> getAllProducts() {
		List<ProductBean> list = new ArrayList<>();
		Session sess = sessionFactory.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		User user = (User) session.getAttribute("auth");
		Query query = sess.createQuery("from Product where isDeleted=1 ");
		list = query.list();
		tx.commit();
		return list;
	}

	@Override
	public int createProduct(Product product) {
		Session sess = sessionFactory.getCurrentSession();
		int rows = (int) sess.save(product);
		return rows;
	}

	@Override
	public int createProduct(ProductBean product) {
		Session sess = sessionFactory.getCurrentSession();
		product.setIsDeleted((byte) 1);
		int rows = (int) sess.save(product.convertProduct());
		return rows;
	}

	@Override
	public void updateProduct(ProductBean product) {

		ProductBean prod = new ProductBean();

		try {
			Session sess = sessionFactory.getCurrentSession();

			prod.setProdName(product.getProdName());
			prod.setProdDesc(product.getProdDesc());
			prod.setProdSellPrice(product.getProdSellPrice());
			prod.setProdCostPrice(product.getProdCostPrice());
			prod.setStockUnit(product.getStockUnit());
			prod.setProdImg(product.getProdImg());
			prod.setUser(product.getUser());
			prod.setId(product.getId());
			prod.setIsDeleted((byte) 1);
			sess.clear();
			sess.update(prod.convertProduct());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e);
		}
	}

	@Override // passing entity
	public Product getProductById(int id) {
		Product bean = new Product();

		try {
			Session sess = sessionFactory.getCurrentSession();
			Transaction tx = sess.beginTransaction();
			bean = sess.get(Product.class, id);
			tx.commit();
			sess.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e);
		}

		return bean;
	}

	@Override
	public void removeProduct(int id) {

		try {
			Session sess = sessionFactory.openSession();
			Transaction tx = sess.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = sess.createQuery("update Product set isDeleted=0 where id=?1");
			query.setParameter(1, id);
			query.executeUpdate();
			tx.commit();
			sess.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}

	}

}

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

import com.spring.prod.entity.Product;
import com.spring.prod.entity.Purchase;
import com.spring.prod.entity.User;

@Repository
@Transactional
public class PurchaseDaoImpl implements PurchaseDao {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertOrder(Purchase purchase) {
		boolean result = false;
		Session sess = sessionFactory.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		sess.save(purchase);
		tx.commit();
		sess.close();
		result = true;
		return result;
	}

	@Override
	public Purchase getPurchaseById(int id) {
		Purchase pur = new Purchase();

		try {
			Session sess = sessionFactory.getCurrentSession();
			Transaction tx = sess.beginTransaction();
			pur = sess.get(Purchase.class, id);
			tx.commit();
			sess.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e);
		}

		return pur;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchase> getAllPurchases() {
		List<Purchase> list = new ArrayList<>();
		Session sess = sessionFactory.openSession();
		User user = (User) session.getAttribute("auth");
		@SuppressWarnings("rawtypes")
		Query query = sess.createQuery("from Purchase where user.id=?1");
		query.setParameter(1, user.getId());
		list = query.list();
		sess.close();
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void cancelOrder(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("delete from Purchase where id=?1");
		query.setParameter(1, id);
		query.executeUpdate();
		tx.commit();
		sess.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchase> getAllCartProducts(List<Purchase> purchases) {
		List<Purchase> list = new ArrayList<>();

		try {
			Session sess = sessionFactory.openSession();
			if (purchases.size() > 0) {
				for (Purchase c : purchases) {
					System.out.println(c.getProduct().getId());
					@SuppressWarnings("rawtypes")
					Query query = sess.createQuery("from Purchase where product.id=?1");
					query.setParameter(1, c.getProduct().getId());
					list = query.list();
				}
			}
			sess.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return list;
	}

	@Override
	public Product updateProduct(Product product) {

		Product prod = new Product();
		try {
			Session sess = sessionFactory.openSession();
			Transaction tx = sess.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = sess.createQuery("update Product set stockUnit=?1 where id=?2");
			query.setParameter(1, product.getStockUnit());
			query.setParameter(2, product.getId());
			query.executeUpdate();
			tx.commit();
			sess.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return prod;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public User updateSellBalance(User user) {
		try {
			Session sess = sessionFactory.openSession();
			Transaction tx = sess.beginTransaction();
			Query query = sess.createQuery("update User set balance=?1 where id=?2");
			query.setParameter(1, user.getBalance());
			query.setParameter(2, user.getId());
			query.executeUpdate();
			tx.commit();
			sess.close();
			System.out.println("User in Dao: "+user);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return user;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public User updateBuyerBal(User user) {
		try {
			Session sess = sessionFactory.openSession();
			Transaction tx = sess.beginTransaction();
			Query query = sess.createQuery("update User set balance=?1 where id=?2");
			query.setParameter(1, user.getBalance());
			query.setParameter(2, user.getId());
			query.executeUpdate();
			tx.commit();
			sess.close();
			System.out.println("User: "+user);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return user;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Purchase> getPurchaseByBuyerId(int id) {
		List<Purchase> list = new ArrayList<>();
		User auth = (User) session.getAttribute("auth");
		try {
			Session sess = sessionFactory.openSession();

			Query q = sess.createQuery("from Purchase where user.id=?1");
			q.setParameter(1, auth.getId());
			list = q.list();
			System.out.println("User Id in dao " + auth.getId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Purchase> getPurchaseBySellerId(int id) {
		List<Purchase> list = new ArrayList<>();
		User auth = (User) session.getAttribute("auth");
		try {
			Session sess = sessionFactory.openSession();
			Query q = sess.createQuery("from Purchase where user1.id=?1");
			q.setParameter(1, auth.getId());
			list = q.list();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return list;
	}

}

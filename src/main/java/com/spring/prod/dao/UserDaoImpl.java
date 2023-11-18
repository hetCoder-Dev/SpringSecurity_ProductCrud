package com.spring.prod.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.prod.bean.UserBean;
import com.spring.prod.entity.User;
import com.spring.prod.entity.UserRole;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}	

	@Override
	@Transactional
	public void registerUser(UserBean user) {
		Session sess = sessionFactory.getCurrentSession();
		user.setPassword(encoder.encode(user.getPassword()));
		sess.save(user.convertToUser());		
	}
	
	@Override
	@Transactional
	public void registerUser(User user) {
		Session sess = sessionFactory.getCurrentSession();
		user.setPassword(encoder.encode(user.getPassword()));
		sess.save(user);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getRoles() {
		List<UserRole> list = new ArrayList<>();
		Transaction tx = null;
		Session sess = sessionFactory.openSession();
		tx = sess.beginTransaction();
		Query<UserRole> q = sess.createQuery("from UserRole");
		list = q.list();
		tx.commit();
		sess.close();
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public User getUserByUsername(String email) {
		User u = null;

		try {
			Session sess = sessionFactory.openSession();			
			Query query = sess.createQuery("from User where email='"+email+"'");
			u = (User) query.uniqueResult();
			sess.close();
			System.out.println("User in Dao "+u);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e);
		}
		return u;
	}

	@Override
	public User getUserById(int id) {
		User user = new User();
		Session sess = sessionFactory.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		user = sess.get(User.class, id);
		tx.commit();
		sess.close();
		return user;
	}

}

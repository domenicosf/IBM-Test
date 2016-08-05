package com.ibm.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ibm.model.User;

import java.util.List;

public class UserDAO {

	public void addUser(User user) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		addUser(session, user);
		tx.commit();
		session.close();

	}

	private void addUser(Session session, User bean) {
		User user = new User();

		user.setName(bean.getName());
		user.setEmail(bean.getEmail());
		user.setStatus(bean.isStatus());

		session.save(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from User");
		List users = query.list();
		return users;
	}

	public int deleteUser(Long id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from User where id = :id";
		Query query = session.createQuery(hql);
		query.setLong("id", id);
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}

}

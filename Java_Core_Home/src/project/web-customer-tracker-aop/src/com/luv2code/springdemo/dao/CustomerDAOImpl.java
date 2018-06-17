package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> query = session.createQuery("FROM Customer ORDER BY lastName", Customer.class);
		List<Customer> customers = query.getResultList();
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomers(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE FROM Customer WHERE id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> theQuery = null;
		if (searchName != null && searchName.trim().length() > 0) {
			theQuery = session.createQuery(
					"FROM Customer WHERE LOWER(firstName) LIKE :theName or LOWER(lastName) LIKE :theName",
					Customer.class);
			theQuery.setParameter("theName", "%" + searchName.toLowerCase() + "%");
		} else {
			theQuery = session.createQuery("from Customer", Customer.class);
		}
		List<Customer> customers = theQuery.getResultList();
		return customers;
	}
}

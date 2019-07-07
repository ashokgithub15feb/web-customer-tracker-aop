package com.luv2code.springdemo.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory - read the session factory object from xml file
	@Autowired
	private SessionFactory sessionFactory; 
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query -- sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//execute query and get the result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save or update
		currentSession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomers(int theId) {

		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/ read from database using the primary key
		Customer customer = currentSession.get(Customer.class, theId);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete customer object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:theCustomerId");
		theQuery.setParameter("theCustomerId", theId);

		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query query = null;

		if (theSearchName != null && theSearchName.trim().length() > 0) {
			// search for first name or last name case insensitive
			query = currentSession.createQuery(
					"from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
					Customer.class);

			query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		} else {
			// theSearchCustomer is empty so just get all customers
			query = currentSession.createQuery("from Customer", Customer.class);
		}

		List<Customer> customers = query.getResultList();

		return customers;
	}

}

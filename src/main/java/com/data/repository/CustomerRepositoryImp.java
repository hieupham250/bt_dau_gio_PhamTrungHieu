package com.data.repository;

import com.data.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImp implements CustomerRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> findAll(String keyword) {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("from Customer where lastName like :keyword", Customer.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    @Override
    public Customer findById(int id) {
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        return customer;
    }

    @Override
    public void save(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
    }

    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        if (customer != null) {
            session.delete(customer);
        }
        transaction.commit();
    }
}

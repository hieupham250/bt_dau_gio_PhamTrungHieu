package com.data.repository;

import com.data.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll(String keyword);
    Customer findById(int id);
    void save(Customer customer);
    void update(Customer customer);
    void delete(int id);
}

package com.data.service;

import com.data.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll(String keyword);
    Customer findById(int id);
    void save(Customer customer);
    void update(Customer customer);
    void delete(int id);
}

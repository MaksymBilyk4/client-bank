package com.clientbank.dev.dao;

import com.clientbank.dev.models.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomerDao implements Dao<Customer>{

    private final List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> findAll() {
        return Collections.unmodifiableList(customers);
    }

    @Override
    public Customer getOne(Long id) {
        Customer customer = null;

        for (Customer c: customers) {
            if (c.getId().equals(id)) {
                customer = c;
                break;
            }
        }

        return customer;
    }

    @Override
    public Customer save(Customer customer) {
        customers.add(customer);
        return customer;
    }

    @Override
    public void saveAll(List<Customer> customerList) {
        customers.addAll(customerList);
    }

    @Override
    public void deleteAll(List<Customer> customerList) {
        customers.removeAll(customerList);
    }

    @Override
    public boolean delete(Customer customer) {
        return customers.remove(customer);
    }

    @Override
    public boolean deleteById(Long id) {
        return customers.removeIf(customer -> id.equals(customer.getId()));
    }

    public boolean update(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customer.getId())) {
                customers.set(i, customer);
                return true;
            }
        }

        return false;
    }
}

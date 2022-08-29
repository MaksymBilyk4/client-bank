package com.clientbank.dev.services;

import com.clientbank.dev.dao.AccountDao;
import com.clientbank.dev.dao.CustomerDao;
import com.clientbank.dev.models.Account;
import com.clientbank.dev.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService implements I_Service<Customer> {

    private final CustomerDao customerDao;
    private final AccountDao accountDao;

    private long customerId = 1;
    private long accountId = 1;

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer getOne(Long id) {
        return customerDao.getOne(id);
    }

    @Override
    public Customer save(Customer customer) {
        customer.setId(customerId++);
        return customerDao.save(customer);
    }

    @Override
    public void saveAll(List<Customer> customers) {
        for (Customer customer: customers) {
            customer.setId(customerId++);
        }
        customerDao.saveAll(customers);
    }

    @Override
    public void deleteAll(List<Customer> customers) {
        customerDao.deleteAll(customers);
    }

    @Override
    public boolean delete(Customer customer) {
        accountDao.deleteAll(customer.getAccounts());
        return customerDao.delete(customer);
    }

    @Override
    public boolean deleteById(Long id) {
        accountDao.deleteAll(getOne(id).getAccounts());
        return customerDao.deleteById(id);
    }

    @Override
    public boolean update(Customer customer) {
        return customerDao.update(customer);
    }

    public Account createAccount(Account account, Long customerId) {
        account.setId(accountId++);
        account.setNumber(UUID.randomUUID().toString());
        Customer customer = customerDao.getOne(customerId).addAccount(account);
        return accountDao.save(account);
    }

    public Customer deleteAccount(Long customerId, Long accountId) {
        return getOne(customerId).deleteAccount(accountId);
    }
}

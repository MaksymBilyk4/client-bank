package com.clientbank.dev.services;

import com.clientbank.dev.dao.AccountDao;
import com.clientbank.dev.exceptions.NoSuchMoneyException;
import com.clientbank.dev.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements I_Service<Account>{

    private final AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account getOne(Long id) {
        return accountDao.getOne(id);
    }

    @Override
    public Account save(Account account) {
        return accountDao.save(account);
    }

    @Override
    public void saveAll(List<Account> accounts) {
        accountDao.saveAll(accounts);
    }

    @Override
    public void deleteAll(List<Account> accounts) {
        accountDao.deleteAll(accounts);
    }

    @Override
    public boolean delete(Account account) {
        return accountDao.delete(account);
    }

    @Override
    public boolean deleteById(Long id) {
        return accountDao.deleteById(id);
    }

    @Override
    public boolean update(Account account) {
        return accountDao.update(account);
    }

    public boolean toUpAccount(String number, Double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Replenishment amount must be more then 0");
        Account account = accountDao.findByNumber(number);
        account.setBalance(account.getBalance() + amount);
        return accountDao.update(account);
    }

    public boolean withdrawMoney(String number, Double amount) {
        Account account = accountDao.findByNumber(number);

        if (amount <= 0) throw new IllegalArgumentException("replenishment amount must be more then 0");
        if (account.getBalance() < amount) throw new NoSuchMoneyException("It`s not such money on your balance");

        account.setBalance(account.getBalance() - amount);
        return accountDao.update(account);
    }

    public boolean transferMoney(String to, String from, Double amount) {
        Account toAccount = accountDao.findByNumber(to);
        Account fromAccount = accountDao.findByNumber(from);

        if (amount <= 0) throw new IllegalArgumentException("Replenishment amount must be more then 0");
        if (fromAccount.getBalance() < amount) throw new NoSuchMoneyException("It`s not such money on your balance");

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountDao.update(toAccount);
        return accountDao.update(fromAccount);
    }

}

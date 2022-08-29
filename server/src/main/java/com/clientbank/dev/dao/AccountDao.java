package com.clientbank.dev.dao;

import com.clientbank.dev.models.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class AccountDao implements Dao<Account>{

    private final List<Account> accounts = new ArrayList<>();

    @Override
    public List<Account> findAll() {
        return Collections.unmodifiableList(accounts);
    }

    @Override
    public Account getOne(Long id) {
        Account account = null;

        for (Account a: accounts) {
            if (a.getId().equals(id)) {
                account = a;
                break;
            }
        }

        return account;
    }

    @Override
    public Account save(Account account) {
        accounts.add(account);
        return account;
    }

    @Override
    public void saveAll(List<Account> accountList) {
        accounts.addAll(accountList);
    }

    @Override
    public void deleteAll(List<Account> accountList) {
        accounts.removeAll(accountList);
    }

    @Override
    public boolean delete(Account account) {
        return accounts.remove(account);
    }

    @Override
    public boolean deleteById(Long id) {
        return accounts.removeIf(account -> id.equals(account.getId()));
    }

    public boolean update(Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId().equals(account.getId())) {
                accounts.set(i, account);
                return true;
            }
        }

        return false;
    }

    public Account findByNumber (String number) {
        return accounts.stream()
                .filter(a -> a.getNumber().equals(number))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No account found with number: " + number));
    }
}

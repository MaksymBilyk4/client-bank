package com.clientbank.dev.resourses;

import com.clientbank.dev.models.Account;
import com.clientbank.dev.models.Customer;
import com.clientbank.dev.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer getOne(@PathVariable(name = "id") Long id) {
        return customerService.getOne(id);
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping
    public boolean update(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable(name = "id") Long id) {
        return customerService.deleteById(id);
    }

    @PostMapping("/{id}/account")
    public Account createAccount(@PathVariable(name = "id") Long id, @RequestBody Account account) {
        return customerService.createAccount(account, id);
    }

    @DeleteMapping("/{id}/account/{accountId}")
    public boolean deleteAccount(
            @PathVariable(name = "id") Long customerId,
            @PathVariable(name = "accountId") Long accountId
    ) {
        return customerService.deleteAccount(accountId);
    }
}
